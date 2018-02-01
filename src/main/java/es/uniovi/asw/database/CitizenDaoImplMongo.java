package es.uniovi.asw.database;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.DuplicateKeyException;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

import es.uniovi.asw.parser.Citizen;
import es.uniovi.asw.reportwriter.WriteReport;
import es.uniovi.asw.reportwriter.WriteReportDefault;

/**
 * DAO implementation for MongoDB database
 * 
 * @author Gonzalo de la Cruz Fernández - UO244583
 *
 */
public class CitizenDaoImplMongo implements CitizenDao {


	private MongoClient mongo;
	private DB db;
	private DBCollection users;
	private WriteReport reporter;
	private Properties properties;

	/**
	 * Default constructor that initializes the database from the constants
	 * specified above
	 */
	@SuppressWarnings("deprecation")
	public CitizenDaoImplMongo() {
		
		if (loadProperties()) {

			this.reporter = new WriteReportDefault();
			this.mongo = new MongoClient(properties.getProperty("host"), Integer
					.parseInt(properties.getProperty("port")));
			this.db = mongo.getDB(properties.getProperty("database"));
			this.users = db.getCollection(properties.getProperty("collection"));

			users.createIndex(new BasicDBObject("id", 1), new BasicDBObject(
					"unique", true));
		}
	}

	/**
	 * Loads the database properties file
	 * 
	 * @return True if we could load the file without problems, false otherwise
	 */
	private boolean loadProperties() {
		try {
			FileInputStream input = new FileInputStream("src/main/resources/database.properties");
			this.properties = new Properties();
			this.properties.load(input);
			return true;
		} catch (Exception e) {
			reporter.report(e, "Error loading database.properties file");
			return false;
		}
	}

	/**
	 * This method is used in the test (for using the database for test)
	 * 
	 * 
	 * @param host
	 * @param port
	 * @param database
	 * @param collection
	 */
	@SuppressWarnings("deprecation")
	public CitizenDaoImplMongo(String host, int port, String database,
			String collection) {
		this.reporter = new WriteReportDefault();
		this.mongo = new MongoClient(host, port);
		this.db = mongo.getDB(database);
		this.users = db.getCollection(collection);

		users.createIndex(new BasicDBObject("id", 1), new BasicDBObject(
				"unique", true));
	}

	/**
	 * 
	 * @param c
	 * 
	 *            Inserts a new document into the database with the citizen
	 *            passed as a parameter.
	 * 
	 */

	@Override
	public boolean insert(Citizen c) {
		BasicDBObject document = new BasicDBObject();
		document.put("firstName", c.getName());
		document.put("lastName", c.getlastName());
		document.put("email", c.getEmail());
		document.put("password", c.getPassword());
		document.put("dateOfBirth", c.getbirthDate());
		document.put("address", c.getAddress());
		document.put("nationality", c.getNationality());
		document.put("id", c.getID());
		document.put("nif", c.getNIF());
		document.put("pollingStation", c.getpollingStation());
		try {
			users.insert(document);
			reporter.logDatabaseInsertion(c);
			return true;
		} catch (DuplicateKeyException me) {
			reporter.report(me, "Error inserting in the database: "
					+ "The inserted Key is in the database");
		} catch (MongoException me) {
			reporter.report(me, "Error inserting in the database");
		}
		return false;

	}

	/**
	 * 
	 * @param ID
	 * 
	 *            Removes a document from the database.
	 * 
	 */

	@Override
	public void remove(String ID) {
		BasicDBObject document = new BasicDBObject();
		document.put("id", ID);
		users.remove(document);
	}

	/**
	 * 
	 * @param ID
	 * 
	 *            Returns a document (citizen) from the database corresponding
	 *            to the id passed as a parameter.
	 * 
	 */

	@Override
	public Citizen findById(String ID) {
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("id", ID);
		DBCursor cursor = users.find(whereQuery);
		Citizen c = null;
		while (cursor.hasNext()) {
			DBObject user = cursor.next();
			c = new Citizen((String) user.get("firstName"), (String) user.get(
					"lastName"), (String) user.get("email"), (Date) user.get(
							"dateOfBirth"), (String) user.get("address"),
					(String) user.get("nationality"), (String) user.get("id"),
					(String) user.get("nif"), (int) user.get("pollingStation"));
		}
		return c;
	}

	/**
	 * 
	 * Returns every document (citizen) in the databse.
	 * 
	 */

	@Override
	public List<Citizen> findAll() {

		List<Citizen> allCitizens = new ArrayList<>();

		DBCursor cursor = users.find();
		while (cursor.hasNext()) {
			DBObject user = cursor.next();
			Citizen c = new Citizen((String) user.get("firstName"),
					(String) user.get("lastName"), (String) user.get("email"),
					(Date) user.get("dateOfBirth"), (String) user.get(
							"address"), (String) user.get("nationality"),
					(String) user.get("id"), (String) user.get("nif"),
					(int) user.get("pollingStation"));
			allCitizens.add(c);
		}

		return allCitizens;
	}

	/**
	 * 
	 * Clears the database.
	 * 
	 */

	@Override
	public void cleanDatabase() {
		users.remove(new BasicDBObject());

	}

}
