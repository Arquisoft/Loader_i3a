package alltests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import databasetests.AllDatabaseTests;
import lettergeneratorstests.AllGeneratorTests;
import parsertests.AllParserTests;

@RunWith(Suite.class)
@SuiteClasses({ AllDatabaseTests.class, AllGeneratorTests.class,
		AllParserTests.class, LoadUsersTest.class })

public class AllTests {

}
