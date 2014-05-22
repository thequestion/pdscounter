/**
 * 
 */
package com.wenbyuan.pdscounter.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wenbyuan.pdscounter.contants.FileDirConstant;
import com.wenbyuan.pdscounter.document.Message;
import com.wenbyuan.pdscounter.document.MesssageWithImpression;
import com.wenbyuan.pdscounter.file.FileReader;
import com.wenbyuan.pdscounter.mapreduce.InvertedIndex;
import com.wenbyuan.pdscounter.mapreduce.MapReduceInMemory;
import com.wenbyuan.pdscounter.parser.Parser;
import com.wenbyuan.pdscounter.parser.xml.XMLParserFactory;
import com.wenbyuan.pdscounter.util.FrequencyCounter;

/**
 * @author wenbyuan
 * 
 */
public class PdsCounterClient {

	private static final int MAX = 10;
	private static final int MIN = 0;
	private Map<String, Long> impressionMap = null;
	private Map<String, Double> attributesWithWeight = null;

	public PdsCounterClient() {
		impressionMap = new HashMap<String, Long>();
		attributesWithWeight = new HashMap<String, Double>();
	}

	public Map<String, Double> getAttributesWithWeight() {
		return this.attributesWithWeight;
	}

	public void run() {
		List<Message> messagesWithImpressionCount = getImpressionCountForMessage();
		Map<String, Long> messageWithImpressionCountMap = 
				aggregateMessagesWithImpressionCount(messagesWithImpressionCount);

		InvertedIndex attributeIndex = getAttributeInvertedIndex();

		calculateWeightForAttribute(attributeIndex,
				messageWithImpressionCountMap);

		// normalize(max);
	}

	private void calculateWeightForAttribute(InvertedIndex attributeIndex,
			Map<String, Long> messageWithImpressionCountMap) {
		double max = Double.MIN_VALUE;
		for (String attribute : attributeIndex.keySet()) {
			double weight = 0;
			FrequencyCounter indexesForMessage = attributeIndex.get(attribute);
			for (String messageId : indexesForMessage.keySet()) {
				long numberOfImpression = getNumberOfImpressionForMessage(
						messageId, messageWithImpressionCountMap);
				int numberOfOccurence = getNumberOfOccurenceForAttributeInMessage(
						messageId, indexesForMessage);

				weight = addWeightForAttribute(weight, numberOfImpression,
						numberOfOccurence);
			}
			if (weight > max)
				max = weight;
			attributesWithWeight.put(attribute, weight);
		}

	}

	private double addWeightForAttribute(double weight,
			long numberOfImpression, int numberOfOccurence) {
		return weight += numberOfImpression * numberOfOccurence;
	}

	private int getNumberOfOccurenceForAttributeInMessage(String messageId,
			FrequencyCounter indexesForMessage) {
		return indexesForMessage.get(messageId);
	}

	private long getNumberOfImpressionForMessage(String messageId,
			Map<String, Long> messageWithImpressionCountMap) {
		return impressionMap.get(messageId) == null ? 0 : impressionMap
				.get(messageId);
	}

	private InvertedIndex getAttributeInvertedIndex() {
		MapReduceInMemory mr = MapReduceInMemory.getInstance();
		mr.run();
		return (InvertedIndex) mr.getAttributeInvertedIndex();
	}

	private Map<String, Long> aggregateMessagesWithImpressionCount(
			List<Message> messagesWithImpressionCount) {
		Map<String, Long> messageWithImpressionCountMap = new HashMap<String, Long>();
		for (Message message : messagesWithImpressionCount) {
			String messageId = ((MesssageWithImpression) message).getMessageId();
			Long numberOfImpression = Long.parseLong(((MesssageWithImpression) message).getNumberOfImpression());
			impressionMap.put(messageId, numberOfImpression);
		}
		return messageWithImpressionCountMap;
	}

	//
	// private void normalize(double max) {
	// if(max == 0) System.exit(0);
	// for(String attribute : attributeMapFinal.keySet()){
	// double weight = attributeMapFinal.get(attribute);
	// double normal = weight/max;
	// attributeMapFinal.put(attribute, normal);
	// }
	//
	// }

	private List<Message> getImpressionCountForMessage() {
		Parser impressionParser = XMLParserFactory.getInstance()
				.createParser("impression");
		for (int i = MIN; i < MAX; i++) {
			FileReader impressionReader = new FileReader(FileDirConstant.IMPRESSION_DIR_PRE + i + FileDirConstant.IMPRESSION_DIR_POST);
			impressionParser.parse(impressionReader.read());
		}
		return (List<Message>) impressionParser.getEntries();
	}

}
