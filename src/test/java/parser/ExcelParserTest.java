package parser;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static uk.org.lidalia.slf4jtest.LoggingEvent.error;
import static uk.org.lidalia.slf4jtest.LoggingEvent.info;
import static uk.org.lidalia.slf4jtest.LoggingEvent.warn;

import org.junit.After;
import org.junit.Test;

import es.uniovi.asw.loader_client.parser.ExcelParser;
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
		new ExcelParser("src/test/resources/test");
		assertThat(logger.getLoggingEvents(),
				is(asList(info("Starting parsing..."), error("Trying to read a file without extension"),
						error("No se ha encontrado el archivo solicitado"), info("Finish parsing..."))));
	}

	@Test
	public void testIncorrectExtension() {
		new ExcelParser("src/test/resources/test.txt");
		assertThat(logger.getLoggingEvents(),
				is(asList(info("Starting parsing..."), error("Trying to read a not valid file with extension: txt"),
						error("No se ha encontrado el archivo solicitado"), info("Finish parsing..."))));

	}

	@Test
	public void testCorrectExtension() {
		new ExcelParser("src/test/resources/testEmpty.xlsx");
		assertThat(logger.getLoggingEvents(), is(asList(info("Starting parsing..."), info("Finish parsing..."))));
	}

	@Test
	public void testNoName() {
		new ExcelParser("src/test/resources/testNoName.xlsx");
		assertThat(logger.getLoggingEvents(),
				is(asList(info("Starting parsing..."), warn("Null name on row number 1"), info("Finish parsing..."))));
	}

	@Test
	public void testNoEmail() {
		new ExcelParser("src/test/resources/testNoEmail.xlsx");
		assertThat(logger.getLoggingEvents(),
				is(asList(info("Starting parsing..."), warn("Null email on row number 1"), info("Finish parsing..."))));
	}

	@Test
	public void testNoId() {
		new ExcelParser("src/test/resources/testNoId.xlsx");
		assertThat(logger.getLoggingEvents(),
				is(asList(info("Starting parsing..."), warn("Null id on row number 1"), info("Finish parsing..."))));
	}

	@Test
	public void testNoLocation() {
		new ExcelParser("src/test/resources/testNoLocation.xlsx");
		assertThat(logger.getLoggingEvents(), is(
				asList(info("Starting parsing..."), warn("Null location on row number 1"), info("Finish parsing..."))));
	}

	@Test
	public void testNoKindCode() {
		new ExcelParser("src/test/resources/testNoKindCode.xlsx");
		assertThat(logger.getLoggingEvents(),
				is(asList(info("Starting parsing..."), warn("Null kind on row number 1"), info("Finish parsing..."))));
	}

	@Test
	public void testDuplicate() {
		new ExcelParser("src/test/resources/testDuplicate.xlsx");
		assertThat(logger.getLoggingEvents(), is(asList(info("Starting parsing..."), info("Agent added"),
				warn("Duplicated citizen on row number 2"), info("Finish parsing..."))));
	}

	@Test
	public void testEmptyRow() {
		new ExcelParser("src/test/resources/testEmptyRow.xlsx");
		assertThat(logger.getLoggingEvents(),
				is(asList(info("Starting parsing..."), warn("Empty row n 1"), info("Finish parsing..."))));
	}

	@Test
	public void testCorrect() {
		new ExcelParser("src/test/resources/test.xlsx");
		assertThat(logger.getLoggingEvents(), is(asList(info("Starting parsing..."), info("Agent added"),
				info("Agent added"), info("Agent added"), info("Finish parsing..."))));
	}

}
