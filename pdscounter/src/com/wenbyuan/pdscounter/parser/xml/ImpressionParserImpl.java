/**
 * 
 */
package com.wenbyuan.pdscounter.parser.xml;

import org.xml.sax.Attributes;

import com.wenbyuan.pdscounter.document.MesssageWithImpression;

/**
 * @author wenbyuan
 * 
 */
public class ImpressionParserImpl extends XMLParserImpl {
	
	private ImpressionParserImpl() {
		super();
	}
	
	public static ImpressionParserImpl newInstance(){
		return new ImpressionParserImpl();
	}

	@Override
	protected XMLParserImpl getXMLParser() {
		return this;
	}

	@Override
	public void startElement(String uri, String name, String qName,
			Attributes atts) {
		if (qName != null && qName.compareToIgnoreCase("ROW") == 0) {

			String messageId = atts.getValue("dbGridColumn1");
			String impressionCount = atts.getValue("dbGridColumn2");

			MesssageWithImpression entry = new MesssageWithImpression(
					messageId, impressionCount);
			super.getEntries().add(entry);
		}
	}

}
