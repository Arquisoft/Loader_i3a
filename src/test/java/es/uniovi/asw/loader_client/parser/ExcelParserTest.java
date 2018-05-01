package es.uniovi.asw.loader_client.parser;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static uk.org.lidalia.slf4jtest.LoggingEvent.error;
import static uk.org.lidalia.slf4jtest.LoggingEvent.info;
import static uk.org.lidalia.slf4jtest.LoggingEvent.warn;

import org.junit.After;
import org.junit.Test;

import logger.Logger;
import uk.org.lidalia.slf4jtest.TestLogger;
import uk.org.lidalia.slf4jtest.TestLoggerFactory;

public class ExcelParserTest {
	private TestLogger logger = TestLoggerFactory.getTestLogger(Logger.class);

	@After
	public void clearLoggers() {
		TestLoggerFactory.clear();
	}

	@Test
	public void testNoExtension() {
		ExcelParser ex = new ExcelParser("src/test/resources/test");
		assertThat(logger.getLoggingEvents(),
				is(asList(info("Starting parsing..."), error("Trying to read a file without extension"),
						error("No se ha encontrado el archivo solicitado"), info("Finish parsing..."))));
		assertTrue(ex.getContent().isEmpty());
	}

	@Test
	public void testIncorrectExtension() {
		ExcelParser ex = new ExcelParser("src/test/resources/test.txt");
		assertThat(logger.getLoggingEvents(),
				is(asList(info("Starting parsing..."), error("Trying to read a not valid file with extension: txt"),
						error("No se ha encontrado el archivo solicitado"), info("Finish parsing..."))));
		assertTrue(ex.getContent().isEmpty());
	}

	@Test
	public void testCorrectExtension() {
		ExcelParser ex = new ExcelParser("src/test/resources/testEmpty.xlsx");
		assertThat(logger.getLoggingEvents(), is(asList(info("Starting parsing..."), info("Finish parsing..."))));
		assertTrue(ex.getContent().isEmpty());
	}

	@Test
	public void testNoName() {
		ExcelParser ex = new ExcelParser("src/test/resources/testNoName.xlsx");
		assertThat(logger.getLoggingEvents(),
				is(asList(info("Starting parsing..."), warn("Null name on row number 1"), info("Finish parsing..."))));
		assertTrue(ex.getContent().isEmpty());
	}

	@Test
	public void testNoEmail() {
		ExcelParser ex = new ExcelParser("src/test/resources/testNoEmail.xlsx");
		assertThat(logger.getLoggingEvents(),
				is(asList(info("Starting parsing..."), warn("Null email on row number 1"), info("Finish parsing..."))));
		assertTrue(ex.getContent().isEmpty());
	}

	@Test
	public void testNoId() {
		ExcelParser ex = new ExcelParser("src/test/resources/testNoId.xlsx");
		assertThat(logger.getLoggingEvents(),
				is(asList(info("Starting parsing..."), warn("Null id on row number 1"), info("Finish parsing..."))));
		assertTrue(ex.getContent().isEmpty());
	}

	@Test
	public void testNoLocation() {
		ExcelParser ex = new ExcelParser("src/test/resources/testNoLocation.xlsx");
		assertThat(logger.getLoggingEvents(),
				is(asList(info("Starting parsing..."), warn("Null latitude on row number 1"),
						warn("Null longitude on row number 2"), info("Finish parsing..."))));
		assertTrue(ex.getContent().isEmpty());
	}

	@Test
	public void testInvalidLocation() {
		ExcelParser ex = new ExcelParser("src/test/resources/testInvalidLocation.xlsx");
		assertThat(logger.getLoggingEvents(),
				is(asList(info("Starting parsing..."), warn("Invalid location format on row number 1"),
						warn("Invalid location format on row number 2"),
						warn("Invalid location format on row number 3"), info("Finish parsing..."))));
		assertTrue(ex.getContent().isEmpty());
	}

	@Test
	public void testNoKindCode() {
		ExcelParser ex = new ExcelParser("src/test/resources/testNoKindCode.xlsx");
		assertThat(logger.getLoggingEvents(),
				is(asList(info("Starting parsing..."), warn("Null kind on row number 1"), info("Finish parsing..."))));
		assertTrue(ex.getContent().isEmpty());
	}

	@Test
	public void testDuplicate() {
		ExcelParser ex = new ExcelParser("src/test/resources/testDuplicate.xlsx");
		assertThat(logger.getLoggingEvents(), is(asList(info("Starting parsing..."), info("Agent added"),
				warn("Duplicated citizen on row number 2"), info("Finish parsing..."))));
		assertFalse(ex.getContent().isEmpty());
		assertTrue(ex.getContent().size() == 1);
	}

	@Test
	public void testEmptyRow() {
		ExcelParser ex = new ExcelParser("src/test/resources/testEmptyRow.xlsx");
		assertThat(logger.getLoggingEvents(),
				is(asList(info("Starting parsing..."), warn("Empty row n 1"), info("Finish parsing..."))));
		assertTrue(ex.getContent().isEmpty());
	}

	@Test
	public void testCorrect() {
		ExcelParser ex = new ExcelParser("src/test/resources/test.xlsx");
		assertThat(logger.getLoggingEvents(), is(asList(info("Starting parsing..."), info("Agent added"),
				info("Agent added"), info("Agent added"), info("Finish parsing..."))));
		assertTrue(ex.getFileFullPath().equals("src/test/resources/test.xlsx"));
		assertFalse(ex.getContent().isEmpty());
		assertTrue(ex.getContent().size() == 3);
	}

	/**
	 * Checks if the logInfo(String m, int n) works fine
	 */
	@Test
	public void testLoggerLogInfo() {
		new ExcelParser("src/test/resources/testEmpty.xlsx");
		Logger.addInfo("New info", 3);
		assertThat(logger.getLoggingEvents(),
				is(asList(info("Starting parsing..."), info("Finish parsing..."), info("New info 3"))));
	}

	@Test
	public void testCorrectLocationFormat() {
		ExcelParser parser = new ExcelParser("src/test/resources/testEmpty.xlsx");
		assertFalse(parser.correctFormatLocation("lat", "-10.26"));
		assertFalse(parser.correctFormatLocation("15.26", "long"));
		assertFalse(parser.correctFormatLocation("lat", "long"));
		assertTrue(parser.correctFormatLocation("15.26", "-10.26"));
	}

}
