/**
 * 
 */
package com.wenbyuan.pdscounter.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wenbyuan
 * Calculates the frequency of words in given document
 */
public class FrequencyCounter extends HashMap<String, Integer>{
	public void count(String word){
		Integer frequency = get(word);
		put(word, frequency == null ? 1 : frequency + 1);
	}
	
	public void count(FrequencyCounter counter){
		for(String key:counter.keySet()){
			int frequency = counter.get(key);
			if(this.containsKey(key)){
				this.put(key,  this.get(key) + frequency );
			}else{
				this.put(key, frequency);
			}
		}
	}
	
	public String toString(){
		StringBuffer result = new StringBuffer("{");
		for(Map.Entry<String, Integer> pair : entrySet()){
			result.append(pair.getKey());
			result.append("=");
			result.append(pair.getValue());
			result.append(", ");
		}
		if(result.length() - 2 < 0)	return null;
		result.delete(result.length()-2, result.length());
		result.append("}");
		return result.toString();
	}
	
}
