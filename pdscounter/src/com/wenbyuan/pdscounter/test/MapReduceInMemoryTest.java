/**
 * 
 */
package com.wenbyuan.pdscounter.test;

import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.wenbyuan.pdscounter.file.FileWriter;
import com.wenbyuan.pdscounter.mapreduce.MapReduceInMemory;
import com.wenbyuan.pdscounter.util.FrequencyCounter;

/**
 * @author wenbyuan
 *
 */
public class MapReduceInMemoryTest {

	MapReduceInMemory mapreduce = null;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		mapreduce = MapReduceInMemory.getInstance();
		mapreduce.run();
	}
	
	@Test
	public void testMapReduce() {
		
		Map<String, FrequencyCounter> map = mapreduce.getAttributeInvertedIndex();
		FileWriter writer = new FileWriter("test/map_reduce_in_memory");
		List<String> result = new ArrayList<String>();
		
		for(String attribute:map.keySet()){
			result.add(attribute + ": " + map.get(attribute));
		}
		
		writer.writeLines(result);
	}
 
	@Test
	public void testSingleton() {
		assertSame(MapReduceInMemory.getInstance(), MapReduceInMemory.getInstance());
	}
}
