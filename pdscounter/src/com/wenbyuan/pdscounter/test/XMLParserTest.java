package com.wenbyuan.pdscounter.test;

import static com.wenbyuan.pdscounter.util.Print.println;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.wenbyuan.pdscounter.contants.FileDirConstant;
import com.wenbyuan.pdscounter.document.Message;
import com.wenbyuan.pdscounter.document.MessageWithAttributeFrequency;
import com.wenbyuan.pdscounter.file.FileReader;
import com.wenbyuan.pdscounter.parser.Parser;
import com.wenbyuan.pdscounter.parser.ParserFactory;
import com.wenbyuan.pdscounter.parser.xml.XMLParserFactory;

public class XMLParserTest {

	ParserFactory factory = null;
	
	@Before
	public void setUp() throws Exception {
		factory = XMLParserFactory.getInstance();
	}

	@Test
	public void testContextParser() {
		Parser contextParser = factory.createParser("context");
		FileReader reader = new FileReader(FileDirConstant.CONTEXT_SEGMENT_DIR);
		String validXML = reader.read();
		contextParser.parse(validXML);
		List<MessageWithAttributeFrequency> entries = 
				(List<MessageWithAttributeFrequency>)contextParser.getEntries();
		println("Context segment:");
		println(entries.get(12));
		println(entries.size());
		println("");
	}

	@Test
	public void testContextParser2() {
		Parser contextParser = factory.createParser("context");
		FileReader reader = new FileReader(FileDirConstant.USER_SEGMENT_DIR);
		String validXML = reader.read();
		contextParser.parse(validXML);
		List<MessageWithAttributeFrequency> entries = 
				(List<MessageWithAttributeFrequency>)contextParser.getEntries();
		println("User segment:");
		println(entries.get(12));
		println(entries.size());
		println("");
	}


	@Test
	public void testContextParser3(){
		Parser contextParser = factory.createParser("context");
		FileReader contextReader = new FileReader(FileDirConstant.CONTEXT_SEGMENT_DIR);
		FileReader userReader = new FileReader(FileDirConstant.USER_SEGMENT_DIR);
		String contextXML = contextReader.read();
		String userXML = userReader.read();
		contextParser.parse(contextXML);
		contextParser.parse(userXML);
		List<MessageWithAttributeFrequency> entries = 
				(List<MessageWithAttributeFrequency>)contextParser.getEntries();
		println("Context & User segment:");
		println(entries.get(12));
		println(entries.size());
		println("");
	}

	
//	@Test
//	public void testContextParser5(){
//		Reader reader = new FileReader("segment_has_pds");
//		Writer writer = new FileWriter("logic_has_pds");
//		String content = reader.read();
//		
//		XMLParserImpl parser = XMLParserFactory.getInstance().createParser("logic");
//		writer.writeLines(parser.parse(content));
//	}
//	
	@Test
	public void testImpressionParser() {
		Parser parser = factory.createParser("impression");
		FileReader reader = new FileReader(FileDirConstant.IMPRESSION_DIR_PRE+
				6+FileDirConstant.IMPRESSION_DIR_POST);
		String xml = reader.read();
		parser.parse(xml);
		List<Message> entries = (List<Message>) parser.getEntries();
		
		println("Impression:");
		println(entries.get(2));
		println(entries.size());
		println("");
	}
	
	
}
