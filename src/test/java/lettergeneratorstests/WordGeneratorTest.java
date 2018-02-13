package lettergeneratorstests;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.assertj.core.util.Files;
import org.junit.Test;

import es.uniovi.asw.parser.Agent;
import es.uniovi.asw.parser.lettergenerators.WordLetterGenerator;

public class WordGeneratorTest {

	@Test
	public void test() {
		WordLetterGenerator wordg = new WordLetterGenerator();
		Agent c = new Agent("Jose Fernandez", "jose@gmail.com", "59842315J", 1);
		wordg.generatePersonalLetter(c);
		File f = new File(c.getID() + ".docx");
		assertTrue(f.exists());
		Files.delete(f);
	}

}
