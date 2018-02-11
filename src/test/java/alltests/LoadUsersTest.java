package alltests;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.bson.Document;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

import es.uniovi.asw.LoadAgents;

public class LoadUsersTest {

	@Before
	public void clearDatabase() {
		@SuppressWarnings("resource")
		MongoClient mongoClient = new MongoClient(
				new MongoClientURI("mongodb://loader:1234@ds237445.mlab.com:37445/aswdb"));
		MongoDatabase db = mongoClient.getDatabase("aswdb");
		db.getCollection("loader").deleteMany(new Document());
		db.getCollection("loader").dropIndexes();
	}

	@Test
	public void testRunInsert() {
		// Clears the database before the test.
		clearDatabase();

		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		// Tests how the data is inserted correctly into the database for the
		// first time.
		System.setOut(new PrintStream(outContent));
		LoadAgents.main("src/test/resources/test2.xlsx");
		assertTrue(outContent.toString().contains("123"));

	}

	@Test
	public void testNoFileError() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setErr(new PrintStream(outContent));
		LoadAgents.main();
		assertTrue(outContent.toString().contains("Input the name of the file."));
	}

}
