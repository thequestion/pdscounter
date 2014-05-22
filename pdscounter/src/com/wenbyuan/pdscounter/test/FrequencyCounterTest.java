/**
 * 
 */
package com.wenbyuan.pdscounter.test;

import static com.wenbyuan.pdscounter.util.Print.println;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.wenbyuan.pdscounter.file.FileReader;
import com.wenbyuan.pdscounter.file.FileWriter;
import com.wenbyuan.pdscounter.file.Reader;
import com.wenbyuan.pdscounter.file.Writer;
import com.wenbyuan.pdscounter.util.FrequencyCounter;

/**
 * @author wenbyuan
 *
 */
public class FrequencyCounterTest {

	String test;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		test = "This is a beautiful hat This one has green color on it";
	}
 
	@Test
	public void test() {
		String[] tests = test.split(" ");
		FrequencyCounter counter = new FrequencyCounter();
		for(String s: tests)
			counter.count(s);
		println(counter);
		counter.count(counter);
		println(counter);
		counter.count("asdsdfefefd");
		counter.count(counter);
		println(counter);
	}

	@Test
	public void test2(){
		Reader reader = new FileReader("data/logic_has_pds");
		Writer writer = new FileWriter("test/frequency_counter");
		List<String> lines = reader.readLines();
		FrequencyCounter counter = new FrequencyCounter();
		for(String line:lines){
			int startIndex = line.indexOf('{');
			int endIndex = line.indexOf('}');
			if(startIndex != -1 && endIndex != -1){
				line = line.substring(startIndex+1, endIndex);
			}
			String[] attributes = line.split(",");
			for(String attribute:attributes){
				counter.count(attribute.trim());
			}
			
		}
		List<String> t = new ArrayList<String>();
		t.addAll(counter.keySet());
		writer.writeLines(t);;
	}
}
