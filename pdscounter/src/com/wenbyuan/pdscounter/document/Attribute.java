/**
 * 
 */
package com.wenbyuan.pdscounter.document;

/**
 * @author wenbyuan
 *
 */
public class Attribute extends Entry{
	private String attributeName;
	
	public Attribute(String attributeName){
		this.attributeName = attributeName;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	
	public String toString(){
		return "Attribute Name: " + this.attributeName;
	}
}
