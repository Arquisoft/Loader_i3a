package lettergeneratorstests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ PDFGeneratorTest.class, WordGeneratorTest.class })
public class AllGeneratorTests {

}
