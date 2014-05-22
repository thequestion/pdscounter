/**
 * 
 */
package com.wenbyuan.pdscounter.parser;

import java.util.List;

/**
 * @author wenbyuan
 *
 */
public interface Parser {
	public List<? extends Object> getEntries();
	public void parse(String content);
}
