/**
 * 
 */
package com.wenbyuan.pdscounter.document;

/**
 * @author wenbyuan
 *
 */
public class AttributeWithImpressionCount implements Comparable<AttributeWithImpressionCount>{
	private String attributeId;
	private double frequency;
	
	public AttributeWithImpressionCount(String attributeId, double frequency){
		this.attributeId = attributeId;
		this.frequency = frequency;
	}
	
	public String getAttributeId() {
		return attributeId;
	}
	public void setAttributeId(String attributeId) {
		this.attributeId = attributeId;
	}
	public double getFrequency() {
		return frequency;
	}
	public void setFrequency(double frequency) {
		this.frequency = frequency;
	}

	@Override
	public String toString(){
		return this.attributeId + " " + this.frequency;
	}
	
	@Override
	public int compareTo(AttributeWithImpressionCount o) {
		if( o == null ) return 0;
		if(this.frequency == o.frequency)	return 0;
		
		return this.frequency > o.frequency ? -1:1;
	}
	
	
}
