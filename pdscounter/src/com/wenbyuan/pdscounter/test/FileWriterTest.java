/**
 * 
 */
package com.wenbyuan.pdscounter.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.wenbyuan.pdscounter.file.FileReader;
import com.wenbyuan.pdscounter.file.FileWriter;

/**
 * @author wenbyuan
 *
 */
public class FileWriterTest {

	FileReader reader = null;
	FileWriter writer = null;
	List<String> lines = null;
	String content = null;
	private static final String FILE_DIR = "test/file_writer";
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		writer = new FileWriter(FILE_DIR);
		reader = new FileReader(FILE_DIR);
		content = "this is a test string";
		lines = new ArrayList<String>();
		
		lines.add("abc");
		lines.add("def");
		lines.add("ghi");
	}

	@Test
	public void testWriteLines() {
		writer.writeLines(lines);
		List<String> linesRead = reader.readLines();
		assertTrue(lines.equals(linesRead));
	}
	
	@Test
	public void testWrite() {
		writer.write(content);
		String contentRead = reader.read();
		assertTrue(content.equals(contentRead));
	}

}
