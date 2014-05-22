/**
 * 
 */
package com.wenbyuan.pdscounter.parser.string;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.wenbyuan.pdscounter.parser.Parser;

/**
 * @author wenbyuan
 *
 */
public class PdsLogicParserImpl implements Parser{
	
	private static final String PDS_SYMBOL = "PPA";
	private static Logger logger = Logger.getLogger(PdsLogicParserImpl.class.getName());
	List<String> pdsAttrList = null;
	
	private PdsLogicParserImpl(){
		pdsAttrList = new ArrayList<String>();
	};

	public static PdsLogicParserImpl newInstance(){
		return new PdsLogicParserImpl();
	}
	
	@Override
	public void parse(String logic){
		if(logic == null)	return;
		
		String[] logicTokens = getLogicTokens(logic);
		setPdsAttributeListFromLogic(logicTokens);
	}
	
	@Override
	public List<? extends Object> getEntries() {
		return pdsAttrList;
	}

	private void setPdsAttributeListFromLogic(String[] logicTokens) {
		for(String logicToken : logicTokens){
			boolean hasPdsAttr = hasPdsAttributes(logicToken);
			if(hasPdsAttr){
				String name = getAttributeName(logicToken);
				pdsAttrList.add(name);
			}
		}
	}

	private String[] getLogicTokens(String logic) {
		return logic.split(";");
	}

	private boolean hasPdsAttributes(String split) {
		return split.indexOf(PDS_SYMBOL) != -1;
	}

	private String getAttributeName(String logicToken) {
		if(logicToken == null || logicToken.length() == 0){
			logger.log(Level.SEVERE, "Fail to get PDS attribute name for: " + logicToken);
			System.exit(0);
		}
		
		int length = logicToken.length();
		int currentPos = 0;
		while(currentPos < length){
			char currentChar = logicToken.charAt(currentPos);
			if(currentChar == ','){
				return logicToken.substring(0, currentPos);
			}
			currentPos++;
		}
		
		return logicToken;
	}


}
