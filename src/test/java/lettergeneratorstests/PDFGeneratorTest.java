package lettergeneratorstests;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.assertj.core.util.Files;
import org.junit.Test;

import es.uniovi.asw.parser.Agent;
import es.uniovi.asw.parser.lettergenerators.PDFLetterGenerator;

public class PDFGeneratorTest {

	@Test
	public void testGeneratePDF() {
		PDFLetterGenerator pdfg = new PDFLetterGenerator();
		Agent c = new Agent("Jose Fernandez", "jose@gmail.com", "59842315J", 1);
		pdfg.generatePersonalLetter(c);
		File f = new File(c.getID() + ".pdf");
		assertTrue(f.exists());
		Files.delete(f);

	}

}
