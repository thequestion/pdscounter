package com.wenbyuan.pdscounter.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.wenbyuan.pdscounter.client.PdsCounterClient;
import com.wenbyuan.pdscounter.document.AttributeWithImpressionCount;
import com.wenbyuan.pdscounter.file.FileWriter;
 
public class PdsCounterClientTest {
	String fileName = "logic";
	PdsCounterClient client = null;
	FileWriter writer = null;
	@Before
	public void setUp() throws Exception {
		client = new PdsCounterClient();
		writer = new FileWriter("test/pds_counter_client");
		client.run();
	}

	@Test	
	public void testSortByFrequency() {
		Map<String, Double> map = client.getAttributesWithWeight();
		List<AttributeWithImpressionCount> result = new ArrayList<AttributeWithImpressionCount>();
		for(String attribute : map.keySet()){
			result.add(new AttributeWithImpressionCount(attribute, map.get(attribute)));
		}
		Collections.sort(result);
		
		writer.writeLines(result);
	}
	
//	public void testSortByPdsId() {
//		Map<String, Double> map = client.getAttributeMapFinal();
//		List<Integer> result = new ArrayList<Integer>();
//		List<String> toFileContent = new ArrayList<String>();
//		for(String attribute:map.keySet()){
//			int id = Integer.parseInt(attribute.split(":")[1]);
//			result.add(id);
//		}
//		Collections.sort(result);
//		for(Integer attributeId:result){
//			String attribute = attributeId.toString();
//			 
//			toFileContent.add(attribute);
//		}
//		writer.writeLines(toFileContent);
//	}

}
