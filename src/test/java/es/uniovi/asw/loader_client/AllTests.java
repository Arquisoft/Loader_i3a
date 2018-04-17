package es.uniovi.asw.loader_client;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import es.uniovi.asw.loader_client.parser.ExcelParserTest;
import es.uniovi.asw.loader_client.types.AgentTest;

@RunWith(Suite.class)
@SuiteClasses({ ExcelParserTest.class, AgentTest.class })
public class AllTests {

}
