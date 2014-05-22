/**
 * 
 */
package com.wenbyuan.pdscounter.test;

import static com.wenbyuan.pdscounter.util.Print.println;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.wenbyuan.pdscounter.parser.Parser;
import com.wenbyuan.pdscounter.parser.string.StringParserFactory;

/**
 * @author wenbyuan
 *
 */
public class PdsLogicParserTest {
	Parser logicParser = null;
	String[] validLogic = {
			"PNL:20476;PPA:10188,0,categoryId;ICH:1;GIT:1;PNL:20476;PPA:10180,0,categoryId;ICH:1;1&#35;NOP;GIT:2;PNL:20476;PPA:10184,0,categoryId;ICH:1;2&#35;NOP;GIT:3;PNL:20476;PPA:10186,0,categoryId;ICH:1;3&#35;NOP;GIT:7;PNL:20481;PPA:10188,0,categoryId;ICH:1;GIT:4;PNL:20481;PPA:10180,0,categoryId;ICH:1;4&#35;NOP;GIT:5;PNL:20481;PPA:10184,0,categoryId;ICH:1;5&#35;NOP;GIT:6;PNL:20481;PPA:10186,0,categoryId;ICH:1;6&#35;NOP;7&#35;NOP;GIT:11;PNL:20486;PPA:10188,0,categoryId;ICH:1;GIT:8;PNL:20486;PPA:10180,0,categoryId;ICH:1;8&#35;NOP;GIT:9;PNL:20486;PPA:10184,0,categoryId;ICH:1;9&#35;NOP;GIT:10;PNL:20486;PPA:10186,0,categoryId;ICH:1;10&#35;NOP;11&#35;NOP;GIT:15;PNL:20625;PPA:10188,0,categoryId;ICH:1;GIT:12;PNL:20625;PPA:10180,0,categoryId;ICH:1;12&#35;NOP;GIT:13;PNL:20625;PPA:10184,0,categoryId;ICH:1;13&#35;NOP;GIT:14;PNL:20625;PPA:10186,0,categoryId;ICH:1;14&#35;NOP;15&#35;NOP;GIT:19;PNL:20697;PPA:10188,0,categoryId;ICH:1;GIT:16;PNL:20697;PPA:10180,0,categoryId;ICH:1;16&#35;NOP;GIT:17;PNL:20697;PPA:10184,0,categoryId;ICH:1;17&#35;NOP;GIT:18;PNL:20697;PPA:10186,0,categoryId;ICH:1;18&#35;NOP;19&#35;NOP;GIF:20;SEG:4,UK_PandA_Segments_20110825_MD_TC_B01CA6B-00_OV_007ET&#36;;20&#35;NOP;GIF:21;SEG:4,GB_UK_RTM_BHVL_banner_HO_20120515_LK_BHVL_HO&#36;;NOT;21&#35;NOP;",
			"PPA:10180",
			"PNL:1200;PNC:p181;NLT;GIF:1;PDL:30 days;DF-Today:0;DSB;PPA:10180,lastUpdate;DGT;1&#35;NOP;GIF:2;PPA:10180,0;NOTNULL;2&#35;NOP;GIF:3;PPA:10180,1;NOTNULL;3&#35;NOP;GIF:4;PPA:10180,2;NOTNULL;4&#35;NOP;GIF:5;PPA:10180,3;NOTNULL;5&#35;NOP;",
	        "PNL:0;PUA:82;SEL:_,,{PNL:11554;PRG:_;POV:PdsCatSiteWeightAspect,categoryId;ICH:1;};LEN;NGT;",
	        "PNL:0;PUA:82;SEL:_,{PRG:_;POV:PdsCatSiteWeightAspect,categoryId;},{PNL:51580;PRG:_;POV:PdsCatSiteWeightAspect,categoryId;ICH:1;PSL:NEXT;PRG:_;PMV:PdsCatSiteWeightAspect,aspect,Brand;POV:AspectAndValues,values;MCH:1;AND;};LEN;NGT;",
	        "1#NOP;PSL:760;PSL:940;PSC:p264;MCH:2,1;",
	        "SEG:9,febseg1;GIF:1;PCCU:loyalty,flashData,code,CUSDYNISS;NOTNULL;1#NOP;",
	        "PNL:279;PNC:p0;ICH:1;",
		};
	String[] invalidLogic = {
	        "PNL:279;PNC:p0;ICH:1",                         // ';' is missing at the end of the rule
	        "PNL:279;UNK:p0;ICH:1;",                         // unknown operation 'UNK'
			";;;",
			"sdf",
			"PPA",
			"",
	};
	String validLogic3 = null;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

//	@Test
//	public void test() {
//		println(logicParser.parse(validLogic));
//		println(logicParser.parse(validLogic2));
//		println(logicParser.parse(validLogic3));
//	}
	
	@Test
	public void testPdsLogicParser() {
		for(String test:validLogic){
			logicParser = StringParserFactory.getInstance().createParser("pds");
			logicParser.parse(test);
			println(logicParser.getEntries());
		}
		for(String test:invalidLogic){
			logicParser = StringParserFactory.getInstance().createParser("pds");
			logicParser.parse(test);
			println(logicParser.getEntries());
		}
	}
	
	@Test
	public void testPdsLogicParser2() {
		logicParser = StringParserFactory.getInstance().createParser("pds");
		logicParser.parse(null);
		assertEquals(0, logicParser.getEntries().size());
	}
	
}
