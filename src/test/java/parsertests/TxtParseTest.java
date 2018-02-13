package parsertests;

import static org.junit.Assert.assertTrue;

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
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

import es.uniovi.asw.parser.Agent;
import es.uniovi.asw.parser.ReadList;
import es.uniovi.asw.parser.readers.TxtReadList;

public class TxtParseTest {

	private Set<Agent> readData;

	@Before
	public void clearDatabase() {
		@SuppressWarnings("resource")
		MongoClient mongoClient = new MongoClient(
				new MongoClientURI("mongodb://loader:1234@ds237445.mlab.com:37445/aswdb"));

		MongoDatabase db = mongoClient.getDatabase("aswdb");
		db.getCollection("loader").deleteMany(new Document());
	}

	@Test
	public void testParse() {
		clearDatabase();
		String resultSt = "[Agent [name=adri miron, location=40.5N30.6W,  email=testemail@uniovi.es, ID=1234, kindCode=1]]";
		String resultTravis = "[Agent [name=adri miron, location=40.5N30.6W,  email=testemail@uniovi.es, ID=1234, kindCode=1]]";
		ReadList rl = new TxtReadList();
		readData = rl.parse("src/test/resources/test.txt");
		assertTrue(readData.toString().equals(resultSt) || readData.toString().equals(resultTravis));

	}

	@Test
	/**
	 * Checks if the report is generated successfully.
	 *
	 */
	public void testNoDNI() {
		SimpleDateFormat formatofilename = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
		String filename = formatofilename.format(new Date()) + ".txt";
		File file = new File(filename);

		ReadList rl = new TxtReadList();
		readData = rl.parse("src/test/resources/test.txt");

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
		SimpleDateFormat formatofilename = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
		String filename = formatofilename.format(new Date()) + ".txt";
		File file = new File(filename);

		ReadList rl = new TxtReadList();
		readData = rl.parse("src/test/resources/test2.txt");

		assertTrue(file.exists());
		Files.delete(file);
	}

	@Test
	/**
	 * Checks if the report is generated successfully when the txt doesn't have
	 * surname
	 *
	 */
	public void testNoSurname() {
		SimpleDateFormat formatofilename = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
		String filename = formatofilename.format(new Date()) + ".txt";
		File file = new File(filename);

		ReadList rl = new TxtReadList();
		readData = rl.parse("src/test/resources/test4.txt");

		assertTrue(file.exists());
		Files.delete(file);
	}

	@Test
	/**
	 * Checks that the report file is generated when the txt doesn't have an
	 * email.
	 *
	 */
	public void testNoEmail() {
		SimpleDateFormat formatofilename = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
		String filename = formatofilename.format(new Date()) + ".txt";
		File file = new File(filename);

		ReadList rl = new TxtReadList();
		readData = rl.parse("src/test/resources/test3.txt");

		assertTrue(file.exists());
		Files.delete(file);
	}

	/**
	 * Checks that the report file is generated when the excel doesn't have a
	 * location
	 *
	 */
	@Test
	public void testNoLocation() {
		SimpleDateFormat formatofilename = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
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
		SimpleDateFormat formatofilename = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
		String filename = formatofilename.format(new Date()) + ".txt";
		File file = new File(filename);

		ReadList rl = new TxtReadList();
		readData = rl.parse("src/test/resources/test9.txt");

		assertTrue(file.exists());
		Files.delete(file);
	}

	/**
	 * Checks that the report file is generated when the txt has no kind
	 *
	 */
	@Test
	public void testNoKind() {
		SimpleDateFormat formatofilename = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
		String filename = formatofilename.format(new Date()) + ".txt";
		File file = new File(filename);

		ReadList rl = new TxtReadList();
		readData = rl.parse("src/test/resources/test9.txt");

		assertTrue(file.exists());
		Files.delete(file);
	}

	/**
	 * Checks that the report file is generated when the citizen is duplicated
	 *
	 */
	@Test
	public void testDuplicate() {
		SimpleDateFormat formatofilename = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
		String filename = formatofilename.format(new Date()) + ".txt";
		File file = new File(filename);

		ReadList rl = new TxtReadList();
		readData = rl.parse("src/test/resources/test7.txt");

		assertTrue(file.exists());
		Files.delete(file);
	}

}
