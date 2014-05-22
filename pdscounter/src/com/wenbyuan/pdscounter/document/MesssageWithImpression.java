/**
 * 
 */
package com.wenbyuan.pdscounter.document;

/**
 * @author wenbyuan
 *
 */
public class MesssageWithImpression extends Message{

	private String numberOfImpression;
	
	public MesssageWithImpression(String messageId, String numberOfImpression) {
		super(messageId);
		this.numberOfImpression = numberOfImpression;
	}

	public String getNumberOfImpression() {
		return numberOfImpression;
	}

	public void setNumberOfImpression(String numberOfImpression) {
		this.numberOfImpression = numberOfImpression;
	}

	@Override
	public String toString() {
		return super.toString() 
				+ " numberOfImpression=" + numberOfImpression;
	}
	
	

}
