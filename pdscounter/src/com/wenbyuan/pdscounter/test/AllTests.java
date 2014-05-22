package com.wenbyuan.pdscounter.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ FileReaderTest.class, FrequencyCounterTest.class,
		PdsLogicParserTest.class, PdsCounterClientTest.class,
		WeightedFrequencyCounterTest.class })
public class AllTests {

}
