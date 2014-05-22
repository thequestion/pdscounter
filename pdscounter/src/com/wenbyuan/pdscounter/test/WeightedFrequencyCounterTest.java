/**
 * 
 */
package com.wenbyuan.pdscounter.test;

import static com.wenbyuan.pdscounter.util.Print.println;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import com.wenbyuan.pdscounter.util.FrequencyCounter;
import com.wenbyuan.pdscounter.util.WeightedFrequencyCounter;


/**
 * @author wenbyuan
 *
 */
public class WeightedFrequencyCounterTest {

	HashMap<String, Integer> map = null;
	FrequencyCounter counter = null;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		String test = "This is a beautiful hat This one has green color on it";
		counter = new FrequencyCounter();
		for(String word : test.split(" "))
			counter.count(word);;
		map = counter;
	}

	@Test
	public void test() {
		WeightedFrequencyCounter counter = new WeightedFrequencyCounter();
		
		for(String key : map.keySet()){
			Integer weight = map.get(key);
			counter.count(key, weight);
		}
		println(counter);
	}

}
