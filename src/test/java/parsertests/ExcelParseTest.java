package parsertests;

import static org.junit.Assert.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

import org.assertj.core.util.Files;
import org.junit.Test;

import es.uniovi.asw.parser.Citizen;
import es.uniovi.asw.parser.ReadList;
import es.uniovi.asw.parser.readers.ExcelReadList;

public class ExcelParseTest {

	private Set<Citizen> readData;

	@Test
	public void testParse() {
		String result = "[Citizen [firstName=Juan, lastName=Torres"
				+ " Pardo, email=juan@example.com, birthDate=Thu Oct"
				+ " 10 00:00:00 CET 1985, address=C/ Federico García Lorca 2,"
				+ " ID=90500084Y, "
				+ "nationality=Español, NIF=1.0, pollingStation=1]]";
		String resultForTravis = "[Citizen [firstName=Juan, lastName=Torres"
				+ " Pardo, email=juan@example.com, birthDate=Thu Oct"
				+ " 10 00:00:00 UTC 1985, address=C/ Federico García Lorca 2,"
				+ " ID=90500084Y, "
				+ "nationality=Español, NIF=1.0, pollingStation=1]]";

		ReadList rl = new ExcelReadList();
		readData = rl.parse("src/test/resources/test2.xlsx");

		assertTrue(readData.toString().equals(result)
				|| readData.toString().equals(resultForTravis));
	}

	@Test
	/**
	 * Checks whether the file exists or not.
	 * 
	 */
	public void fileNotFound() {
		SimpleDateFormat formatofilename = new SimpleDateFormat("dd-MM-yyyy",
				Locale.getDefault());
		String filename = formatofilename.format(new Date()) + ".txt";
		File file = new File(filename);

		ReadList rl = new ExcelReadList();
		readData = rl.parse("archivoQueNoExiste");

		assertTrue(file.exists());
		Files.delete(file);
	}

	@Test
	/**
	 * Checks if the report is generated successfully.
	 * 
	 */
	public void testNoDNI() {
		SimpleDateFormat formatofilename = new SimpleDateFormat("dd-MM-yyyy",
				Locale.getDefault());
		String filename = formatofilename.format(new Date()) + ".txt";
		File file = new File(filename);

		ReadList rl = new ExcelReadList();
		readData = rl.parse("src/test/resources/test3.xlsx");

		assertTrue(file.exists());
		Files.delete(file);
	}

	/**
	 * Checks that the report file is generated when the excel doesn't have a
	 * name
	 * 
	 */
	@Test
	public void testNoName() {
		SimpleDateFormat formatofilename = new SimpleDateFormat("dd-MM-yyyy",
				Locale.getDefault());
		String filename = formatofilename.format(new Date()) + ".txt";
		File file = new File(filename);

		ReadList rl = new ExcelReadList();
		readData = rl.parse("src/test/resources/test4.xlsx");

		assertTrue(file.exists());
		Files.delete(file);
	}

	/**
	 * Checks that the report file is generated when the excel doesn't have a
	 * surname
	 * 
	 */
	@Test
	public void testNoSurname() {
		SimpleDateFormat formatofilename = new SimpleDateFormat("dd-MM-yyyy",
				Locale.getDefault());
		String filename = formatofilename.format(new Date()) + ".txt";
		File file = new File(filename);

		ReadList rl = new ExcelReadList();
		readData = rl.parse("src/test/resources/test5.xlsx");

		assertTrue(file.exists());
		Files.delete(file);
	}

	/**
	 * Checks that the report file is generated when the excel doesn't have a
	 * birthdate
	 * 
	 */
	@Test
	public void testNoBirthDate() {
		SimpleDateFormat formatofilename = new SimpleDateFormat("dd-MM-yyyy",
				Locale.getDefault());
		String filename = formatofilename.format(new Date()) + ".txt";
		File file = new File(filename);

		ReadList rl = new ExcelReadList();
		readData = rl.parse("src/test/resources/test6.xlsx");

		assertTrue(file.exists());
		Files.delete(file);
	}

	/**
	 * Checks that the report file is generated when the excel doesn't have the
	 * NIF
	 * 
	 */
	@Test
	public void testNoNIF() {
		SimpleDateFormat formatofilename = new SimpleDateFormat("dd-MM-yyyy",
				Locale.getDefault());
		String filename = formatofilename.format(new Date()) + ".txt";
		File file = new File(filename);

		ReadList rl = new ExcelReadList();
		readData = rl.parse("src/test/resources/test7.xlsx");

		assertTrue(file.exists());
		Files.delete(file);
	}

	/**
	 * Checks that the report file is generated when the excel doesn't have an
	 * address
	 * 
	 */
	@Test
	public void testNoAddress() {
		SimpleDateFormat formatofilename = new SimpleDateFormat("dd-MM-yyyy",
				Locale.getDefault());
		String filename = formatofilename.format(new Date()) + ".txt";
		File file = new File(filename);

		ReadList rl = new ExcelReadList();
		readData = rl.parse("src/test/resources/test8.xlsx");

		assertTrue(file.exists());
		Files.delete(file);
	}

	/**
	 * Checks that the report file is generated when the excel has a blank row.
	 * 
	 */
	@Test
	public void testNoRow() {
		SimpleDateFormat formatofilename = new SimpleDateFormat("dd-MM-yyyy",
				Locale.getDefault());
		String filename = formatofilename.format(new Date()) + ".txt";
		File file = new File(filename);

		ReadList rl = new ExcelReadList();
		readData = rl.parse("src/test/resources/test10.xlsx");

		assertTrue(file.exists());
		Files.delete(file);
	}

	/**
	 * Checks that the report file is generated when the citizen is duplicated
	 * 
	 */
	@Test
	public void testNoDuplicate() {
		SimpleDateFormat formatofilename = new SimpleDateFormat("dd-MM-yyyy",
				Locale.getDefault());
		String filename = formatofilename.format(new Date()) + ".txt";
		File file = new File(filename);

		ReadList rl = new ExcelReadList();
		readData = rl.parse("src/test/resources/test9.xlsx");

		assertTrue(file.exists());
		Files.delete(file);
	}

}
