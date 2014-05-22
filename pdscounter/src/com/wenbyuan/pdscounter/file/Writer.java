/**
 * 
 */
package com.wenbyuan.pdscounter.file;

import java.util.List;


/**
 * @author wenbyuan
 *
 */
public abstract class Writer {
	public abstract void writeLines(List<? extends Object> lines);
	public abstract void write(String content);
}
