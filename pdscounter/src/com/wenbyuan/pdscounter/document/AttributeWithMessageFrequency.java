/**
 * 
 */
package com.wenbyuan.pdscounter.document;

import com.wenbyuan.pdscounter.util.FrequencyCounter;

/**
 * @author wenbyuan
 *
 */
public class AttributeWithMessageFrequency extends Attribute{

	FrequencyCounter messageCounter = null;
	
	public AttributeWithMessageFrequency(String attributeName,
			FrequencyCounter messageCounter) {
		super(attributeName);
		this.messageCounter = messageCounter;
	}

	public FrequencyCounter getMessageCounter() {
		return messageCounter;
	}

	public void setMessageCounter(FrequencyCounter messageCounter) {
		this.messageCounter = messageCounter;
	}

	@Override
	public String toString() {
		return super.toString() + " messageCounter="
				+ messageCounter;
	}

	

}
