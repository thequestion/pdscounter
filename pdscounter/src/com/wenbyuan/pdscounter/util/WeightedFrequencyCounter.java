/**
 * 
 */
package com.wenbyuan.pdscounter.util;

/**
 * @author wenbyuan
 *
 */
public class WeightedFrequencyCounter extends FrequencyCounter{
	public void count(String word, Integer weight){
		Integer frequency = get(word);
		put(word, frequency == null ? weight : frequency + weight);
	}
}
