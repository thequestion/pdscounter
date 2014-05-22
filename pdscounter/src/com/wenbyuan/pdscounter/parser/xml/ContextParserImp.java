/**
 * 
 */
package com.wenbyuan.pdscounter.parser.xml;

import java.util.List;

import org.xml.sax.Attributes;

import com.wenbyuan.pdscounter.document.MessageWithAttributeFrequency;
import com.wenbyuan.pdscounter.parser.Parser;
import com.wenbyuan.pdscounter.parser.string.StringParserFactory;
import com.wenbyuan.pdscounter.util.FrequencyCounter;

/**
 * @author wenbyuan
 *
 */
public class ContextParserImp extends XMLParserImpl{
	
	private ContextParserImp(){
		super();
	}
	
	public static ContextParserImp newInstance(){
		return new ContextParserImp();
	}
	
	@Override
    protected XMLParserImpl getXMLParser() {
		return this;
	}

	@Override
	public void startElement(String uri, String name,
		      String qName, Attributes atts){
    	if(qName != null && qName.compareToIgnoreCase("ROW") == 0){
    		String messageId = atts.getValue("dbGridColumn2");
        	String logic = atts.getValue("dbGridColumn3");
        	
        	FrequencyCounter counter = countFrequency(logic);
        	
        	MessageWithAttributeFrequency entry = new MessageWithAttributeFrequency(messageId, counter);
        	super.getEntries().add(entry);
    	}
	}

	private FrequencyCounter countFrequency(String logic) {
    	Parser parser = StringParserFactory.getInstance().createParser("pds");
    	parser.parse(logic);
    	List<String> attributes = (List<String>) parser.getEntries();
    	FrequencyCounter counter = new FrequencyCounter();
    	for(String attribute:attributes){
    		counter.count(attribute);
    	}
    	//Assupmtion 1 : for each message, one pds attribute only counts once
    	for(String attribute:counter.keySet()){
    		counter.put(attribute, 1);
    	}
    	return counter;
	}

}
