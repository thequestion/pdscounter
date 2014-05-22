/**
 * 
 */
package com.wenbyuan.pdscounter.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author wenbyuan
 * 
 */
public class FileWriter extends Writer {
	private File file = null;

	public FileWriter(String fileName) {
		file = new File(fileName);
		if (!file.exists())
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	@Override
	public void writeLines(List<? extends Object> lines){
		try {
			PrintWriter out = new PrintWriter(
					file.getAbsoluteFile());
			try{
				for(Object line:lines)
					out.println(line.toString());
			} finally{
				out.close();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	@Override
	public void write(String content) {
		
		try {
			PrintWriter out = new PrintWriter(
					file.getAbsoluteFile());
				try {
					out.print(content);
				} finally{
					out.close();
				}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

}
