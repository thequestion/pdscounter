package com.wenbyuan.pdscounter.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

import com.wenbyuan.pdscounter.parser.ParserFactory;
import com.wenbyuan.pdscounter.parser.string.PdsLogicParserImpl;
import com.wenbyuan.pdscounter.parser.string.StringParserFactory;
import com.wenbyuan.pdscounter.parser.xml.ContextParserImp;
import com.wenbyuan.pdscounter.parser.xml.ImpressionParserImpl;
import com.wenbyuan.pdscounter.parser.xml.XMLParserFactory;
import com.wenbyuan.pdscounter.parser.xml.XMLParserImpl;

public class ParserFactoryTest {
 
	ParserFactory factory = null;
	@Before
	public void setUp() throws Exception {
		factory = XMLParserFactory.getInstance();
	}

	@Test
	public void testCreateWithInvalidArgument() {
		assertNull(factory.createParser(null));
		assertNotNull(factory.createParser(""));
		assertNotNull(factory.createParser("asdf"));
	}

	@Test
	public void testCreateContextParser() {
		assertNotNull(factory.createParser("context"));
		assertEquals(ContextParserImp.class.getName(),
				factory.createParser("context").getClass().getName());
	}
	
	@Test
	public void testCreateImpressionParser() {
		assertNotNull(factory.createParser("impression"));
		assertEquals(ImpressionParserImpl.class.getName(),
				factory.createParser("impression").getClass().getName());
	}
	
	@Test
	public void testCreatePdsLogicParser() {
		assertNotNull(factory.createParser("pds"));
		assertEquals(XMLParserImpl.class.getName(),
				factory.createParser("pds").getClass().getName());
		
		factory = StringParserFactory.getInstance();
		assertEquals(PdsLogicParserImpl.class.getName(),
				factory.createParser("pds").getClass().getName());
		
		assertNull(factory.createParser(null));
		assertNull(factory.createParser("sdadf"));
	}
	
	@Test
	public void testSingleton() {
		factory = XMLParserFactory.getInstance();
		assertSame(XMLParserFactory.getInstance(), XMLParserFactory.getInstance());
		factory = StringParserFactory.getInstance();
		assertSame(StringParserFactory.getInstance(), StringParserFactory.getInstance());
	}
	
}
