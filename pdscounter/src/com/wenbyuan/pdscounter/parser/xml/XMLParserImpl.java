/**
 * 
 */
package com.wenbyuan.pdscounter.parser.xml;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import com.wenbyuan.pdscounter.document.Message;
import com.wenbyuan.pdscounter.parser.Parser;

/**
 * @author wenbyuan
 *
 */
public class XMLParserImpl extends DefaultHandler implements Parser{
	
	private List<Message> entries = null;
	private static Logger logger = Logger.getLogger(XMLParserImpl.class.getName());
	
	protected XMLParserImpl(){
		super();
		entries = new ArrayList<Message>();
	}
	
	public static XMLParserImpl newInstance(){
		return new XMLParserImpl();
	}

    protected XMLParserImpl getXMLParser() {
		return this;
	}
	
	public List<Message> getEntries() {
		return entries;
	}
	public void setEntries(List<Message> entries) {
		this.entries = entries;
	}
	
	@Override
	public void parse(String xml) {
		SAXParser parser;
		XMLReader xr;
		try {
			parser = SAXParserFactory.newInstance().newSAXParser();
			xr = parser.getXMLReader();
			XMLParserImpl handler = getXMLParser();
			xr.setContentHandler(handler);
			xr.setErrorHandler(handler);
			
			logger.info("Parser used: " + handler.getClass().getName());

			xr.parse(new InputSource(new StringReader(xml)));
		} catch (IOException e) {
			logger.log(Level.SEVERE, e.getMessage());
			e.printStackTrace();
		} catch (SAXException e) {
			logger.log(Level.SEVERE, e.getMessage());
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			logger.log(Level.SEVERE, e.getMessage());
			e.printStackTrace();
		}
	}
 
    
	public void startDocument ()
    {
    }


    public void endDocument ()
    {
	//System.out.println("End document");
    }
    

    public void startElement (String uri, String name,
			      String qName, Attributes atts){
    };


    public void endElement (String uri, String name, String qName){
    	//if(qName != null)	System.out.println(qName);
    }


}
