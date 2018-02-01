package parsertests;

import static org.junit.Assert.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

import org.assertj.core.util.Files;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import es.uniovi.asw.parser.Citizen;
import es.uniovi.asw.parser.ReadList;
import es.uniovi.asw.parser.readers.TxtReadList;

public class TxtParseTest {

	private Set<Citizen> readData;

	@Before
	public void clearDatabase() {
		@SuppressWarnings("resource")
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase db = mongoClient.getDatabase("Citizens");
		db.getCollection("users").deleteMany(new Document());
	}

	@Test
	public void testParse() {
		clearDatabase();
		String resultSt = "[Citizen [firstName=adri, lastName=miron, email=testemail@uniovi.es, "
				+ "birthDate=Sun May 19 00:00:00 CEST 1996, address=C/Asturias, "
				+ "ID=testid, nationality=camboya, NIF=1234, pollingStation=1]]";
		String resultTravis = "[Citizen [firstName=adri, lastName=miron, email=testemail@uniovi.es, "
				+ "birthDate=Sun May 19 00:00:00 UTC 1996, address=C/Asturias, "
				+ "ID=testid, nationality=camboya, NIF=1234, pollingStation=1]]";
		ReadList rl = new TxtReadList();
		readData = rl.parse("src/test/resources/test.txt");
		assertTrue(readData.toString().equals(resultSt)
				|| readData.toString().equals(resultTravis));

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

		ReadList rl = new TxtReadList();
		readData = rl.parse("src/test/resources/test3.txt");

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

		ReadList rl = new TxtReadList();
		readData = rl.parse("src/test/resources/test4.txt");

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

		ReadList rl = new TxtReadList();
		readData = rl.parse("src/test/resources/test5.txt");

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

		ReadList rl = new TxtReadList();
		readData = rl.parse("src/test/resources/test6.txt");

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

		ReadList rl = new TxtReadList();
		readData = rl.parse("src/test/resources/test7.txt");

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

		ReadList rl = new TxtReadList();
		readData = rl.parse("src/test/resources/test8.txt");

		assertTrue(file.exists());
		Files.delete(file);
	}

	/**
	 * Checks that the report file is generated when the txt has a blank row
	 * 
	 */
	@Test
	public void testNoRow() {
		SimpleDateFormat formatofilename = new SimpleDateFormat("dd-MM-yyyy",
				Locale.getDefault());
		String filename = formatofilename.format(new Date()) + ".txt";
		File file = new File(filename);

		ReadList rl = new TxtReadList();
		readData = rl.parse("src/test/resources/test10.txt");

		assertTrue(file.exists());
		Files.delete(file);
	}

	/**
	 * Checks that the report file is generated when the citizen is duplicated
	 * 
	 */
	@Test
	public void testDuplicate() {
		SimpleDateFormat formatofilename = new SimpleDateFormat("dd-MM-yyyy",
				Locale.getDefault());
		String filename = formatofilename.format(new Date()) + ".txt";
		File file = new File(filename);

		ReadList rl = new TxtReadList();
		readData = rl.parse("src/test/resources/test9.txt");

		assertTrue(file.exists());
		Files.delete(file);
	}

}
