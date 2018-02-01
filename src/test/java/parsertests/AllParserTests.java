package parsertests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CitizenTest.class, ExcelParseTest.class, PasswordTest.class,
		SingletonParserTest.class, TxtParseTest.class })
public class AllParserTests {

}
