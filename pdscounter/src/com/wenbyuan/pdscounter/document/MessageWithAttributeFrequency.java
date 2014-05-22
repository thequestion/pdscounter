/**
 * 
 */
package com.wenbyuan.pdscounter.document;

import com.wenbyuan.pdscounter.util.FrequencyCounter;

/**
 * @author wenbyuan
 *
 */
public class MessageWithAttributeFrequency extends Message{

	//private List<String>	pdsAttributes;
	//private Map<String, Integer> pdsAttributes;
	private FrequencyCounter pdsAttrMap;

	
	public MessageWithAttributeFrequency(String messageId, FrequencyCounter counter) {
		super(messageId);
		this.pdsAttrMap = counter;
		// TODO Auto-generated constructor stub
	}
//	public DocumentRawEntry(String messageId, Map<String, Integer> pdsAttributes){
//		super(messageId);
//		this.pdsAttributes = pdsAttributes;
//	}
//	
//	public Map<String, Integer> getPdsAttributes() {
//		return pdsAttributes;
//	}
//
//	public void setPdsAttributes(Map<String, Integer> pdsAttributes) {
//		this.pdsAttributes = pdsAttributes;
//	}
	public FrequencyCounter getPdsAttrMap() {
		return pdsAttrMap;
	}

	public void setPdsAttrMap(FrequencyCounter pdsAttrMap) {
		this.pdsAttrMap = pdsAttrMap;
	}
	@Override
	public String toString() {
		return super.toString() + " pdsAttrMap=" + pdsAttrMap;
	}
	
	
}
