/**
 * 
 */
package com.wenbyuan.pdscounter.test;

import static com.wenbyuan.pdscounter.util.Print.println;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.wenbyuan.pdscounter.file.FileReader;

/**
 * @author wenbyuan
 *
 */
public class FileReaderTest {
	String fileName = null;
	FileReader reader = null;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		fileName = "data/user_segment_test";
		reader = new FileReader(fileName);
	}

	@Test
	public void testRead() {
		String content = reader.read();
		println(content);
	}
	 
	@Test
	public void testReadLines() {
		List<String> lines = reader.readLines();
		println(lines);
	}

}
