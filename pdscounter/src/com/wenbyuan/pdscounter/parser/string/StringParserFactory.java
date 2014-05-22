/**
 * 
 */
package com.wenbyuan.pdscounter.parser.string;

import com.wenbyuan.pdscounter.parser.Parser;
import com.wenbyuan.pdscounter.parser.ParserFactory;

/**
 * @author wenbyuan
 *
 */
public class StringParserFactory implements ParserFactory{

	private static StringParserFactory instance = null;
	
	private StringParserFactory(){}
	
	/**
	 * Singleton
	 */
	public static StringParserFactory getInstance(){
		if(instance == null){
			instance = new StringParserFactory();
		}
		return instance;
	}
	@Override
	public Parser createParser(String parserName) {
		if(parserName == null) return null;
		
		if(parserName.compareToIgnoreCase("PDS") == 0){
			return PdsLogicParserImpl.newInstance();
		} else {
			return null;
		}
	}

}
