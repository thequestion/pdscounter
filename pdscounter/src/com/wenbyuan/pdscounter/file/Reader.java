package com.wenbyuan.pdscounter.file;
/**
 * 
 */


import java.util.List;

/**
 * @author wenbyuan
 *
 */
public abstract class Reader {
	public abstract List<String> readLines();
	public abstract String read();
}
