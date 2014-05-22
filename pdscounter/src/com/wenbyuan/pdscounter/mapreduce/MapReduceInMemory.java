/**
 * 
 */
package com.wenbyuan.pdscounter.mapreduce;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.wenbyuan.pdscounter.contants.FileDirConstant;
import com.wenbyuan.pdscounter.document.Message;
import com.wenbyuan.pdscounter.document.MessageWithAttributeFrequency;
import com.wenbyuan.pdscounter.file.FileReader;
import com.wenbyuan.pdscounter.file.FileWriter;
import com.wenbyuan.pdscounter.file.Reader;
import com.wenbyuan.pdscounter.parser.Parser;
import com.wenbyuan.pdscounter.parser.xml.XMLParserFactory;
import com.wenbyuan.pdscounter.util.FrequencyCounter;

/**
 * @author wenbyuan
 *
 */
public class MapReduceInMemory {

	private static Logger logger = Logger.getLogger(MapReduceInMemory.class.getName());

	private static MapReduceInMemory instance = null;
	private InvertedIndex attributeIndex = null;
	private InvertedIndex messageIndex = null;
	private static final boolean IS_DEBUG = true;

	private static final String DEBUG_ATTRIBUTE_INDEX_DIR = "debug/attribute_index";
	private static final String DEBUG_MESSAGE_INDEX_DIR = "debug/message_index";
	
	private MapReduceInMemory(){
		messageIndex = new InvertedIndex();
		attributeIndex = new InvertedIndex();
	};
	
	public static MapReduceInMemory getInstance(){
		if(instance == null){
			instance = new MapReduceInMemory();
		}
		return instance;
	}

	public void run() {
		logger.info( "Start mapping...");
		map();
		logger.info( "End mapping...");
		logger.info( "Start reducing...");
		reduce();
		logger.info( "End reducing...");
	}
	
	private void map() {
		Parser parser = XMLParserFactory.getInstance().createParser("context");
		List<String> contentList = readContentFromFile();
		List<Message> entries = parseContent(contentList, parser);
		
		for(Message entry : entries){
			String messageId = ((MessageWithAttributeFrequency) entry).getMessageId();
			FrequencyCounter counter = ((MessageWithAttributeFrequency) entry).getPdsAttrMap();
			addMessageToIndex(messageId, counter);
		}
		
		if(IS_DEBUG){
			logger.info("Debugging....Write to file");
			writeMessageIndexToFile(messageIndex);
		}
	}
	
	private void addMessageToIndex(String messageId, FrequencyCounter counter) {
		if(messageIndex.containsKey(messageId)){
			FrequencyCounter temp = messageIndex.get(messageId);
			temp.count(counter);
			messageIndex.put(messageId, temp);
		}else{
			messageIndex.put(messageId, counter);
		}
	}

	private List<Message> parseContent(List<String> contentList,
			Parser parser) {
		for(String content : contentList){
			parser.parse(content);
		}
		return (List<Message>)parser.getEntries();
	}

	private List<String> readContentFromFile(){
		Reader reader1 = new FileReader(FileDirConstant.USER_SEGMENT_DIR);
		Reader reader2 = new FileReader(FileDirConstant.CONTEXT_SEGMENT_DIR);
		List<String> contentList = new ArrayList<String>();
		
		String content1 = reader1.read();
		String content2 = reader2.read();
		contentList.add(content1);
		contentList.add(content2);
		
		return contentList;
	}
	
	private void writeMessageIndexToFile(InvertedIndex messegeIndex) {
		FileWriter writer = new FileWriter(DEBUG_MESSAGE_INDEX_DIR);

		List<String> output = new ArrayList<String>();
		String headline = "Message Id" + "     " + "Attribute Map";
		output.add(headline);
		
		for(String message : messegeIndex.keySet()){
			StringBuffer buffer = new StringBuffer();
			buffer.append(message);
			buffer.append("     ");
			buffer.append(messegeIndex.get(message));
			output.add(buffer.toString());
		}
		writer.writeLines(output);		
	}

	private void reduce() {
		for(String message : messageIndex.keySet()){
			FrequencyCounter attributesInMessage = messageIndex.get(message);
			for(String attribute : attributesInMessage.keySet()){
				int attributeOccurencePerMessage = attributesInMessage.get(attribute);
				putInAttributeIndex(attribute, message, attributeOccurencePerMessage);
			}
		}
		
		if(IS_DEBUG){
			logger.info("Debugging....Write to file");
			writeAttributeIndexToFile(attributeIndex);
		}
	}

	private void putInAttributeIndex(String attribute, String message, int attributeOccurencePerMessage) {
		FrequencyCounter messagesPerAttributeMap = null;
		//Map<String, Integer> messagesPerAttributeMap = null;
		if(attributeIndex.containsKey(attribute)){
			messagesPerAttributeMap = attributeIndex.get(attribute);
		}else{
			messagesPerAttributeMap = new FrequencyCounter();
		}
		messagesPerAttributeMap.put(message, attributeOccurencePerMessage);
		attributeIndex.put(attribute, messagesPerAttributeMap);
	}

	private void writeAttributeIndexToFile(Map<String, FrequencyCounter> attributeMap) {
		FileWriter writer = new FileWriter(DEBUG_ATTRIBUTE_INDEX_DIR);

		List<String> output = new ArrayList<String>();
		String headline = "Attribute Id" + "     " + "Message Map";
		output.add(headline);
		
		for(String attribute : attributeMap.keySet()){
			StringBuffer buffer = new StringBuffer();
			buffer.append(attribute);
			buffer.append("     ");
			buffer.append(attributeMap.get(attribute));
			output.add(buffer.toString());
		}
		writer.writeLines(output);	
	}

	public InvertedIndex getAttributeInvertedIndex(){
		return this.attributeIndex;
	}
	
}
