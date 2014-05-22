/**
 * 
 */
package com.wenbyuan.pdscounter.parser.xml;

import java.util.logging.Logger;

import com.wenbyuan.pdscounter.parser.Parser;
import com.wenbyuan.pdscounter.parser.ParserFactory;

/**
 * @author wenbyuan
 *
 */
public class XMLParserFactory implements ParserFactory{

	private static XMLParserFactory instance = null;
	private static Logger logger = Logger.getLogger(XMLParserFactory.class.getName());
	
	private XMLParserFactory(){}
	
	public static XMLParserFactory getInstance(){
		if( instance == null ){
			instance =  new XMLParserFactory();
		}
		return instance;
	}
	
	
	public Parser createParser(String parserName){
		if( parserName == null ) return null;
		try{
			if( parserName.equalsIgnoreCase("context") ){
				return ContextParserImp.newInstance();
			}else if( parserName.equalsIgnoreCase("impression") ){
				return ImpressionParserImpl.newInstance();
			}else{
				return XMLParserImpl.newInstance();
			}			
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		
		return null;
	}
}
