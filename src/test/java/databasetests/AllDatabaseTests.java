package databasetests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AgentDaoMongoTest.class, PersistanceFactoryTest.class })
public class AllDatabaseTests {

}
