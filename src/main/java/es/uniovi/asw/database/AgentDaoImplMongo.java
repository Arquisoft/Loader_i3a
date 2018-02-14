package es.uniovi.asw.database;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.DuplicateKeyException;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;

import es.uniovi.asw.parser.Agent;
import es.uniovi.asw.reportwriter.WriteReport;
import es.uniovi.asw.reportwriter.WriteReportDefault;

/**
 * DAO implementation for MongoDB database
 * 
 * @author Gonzalo de la Cruz Fernández - UO244583
 *
 */
public class AgentDaoImplMongo implements AgentDao {

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
	public AgentDaoImplMongo() {
		this.reporter = new WriteReportDefault();
		if (loadProperties()) {

			this.mongo = new MongoClient(new MongoClientURI("mongodb://loader:1234@ds237445.mlab.com:37445/aswdb"));
			this.db = mongo.getDB(properties.getProperty("database"));
			this.users = db.getCollection(properties.getProperty("collection"));

			users.createIndex(new BasicDBObject("id", 1), new BasicDBObject("unique", true));
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
	public AgentDaoImplMongo(String host, int port, String database, String collection) {
		this();
		this.reporter = new WriteReportDefault();
		this.db = mongo.getDB(database);
		this.users = db.getCollection(collection);

		users.createIndex(new BasicDBObject("id", 1), new BasicDBObject("unique", true));
	}

	/**
	 * @param c
	 *            Inserts a new document into the database with the agent passed
	 *            as a parameter.
	 */

	@Override
	public boolean insert(Agent c) {
		BasicDBObject document = new BasicDBObject();
		document.put("name", c.getName());
		document.put("location", c.getLocation());
		document.put("email", c.getEmail());
		document.put("id", c.getID());
		document.put("password", c.getPassword());
		document.put("kind", c.getKindCode());
		try {
			users.insert(document);
			reporter.logDatabaseInsertion(c);
			return true;
		} catch (DuplicateKeyException me) {
			reporter.report(me, "Error inserting in the database: " + "The inserted Key is in the database");
		} catch (MongoException me) {
			reporter.report(me, "Error inserting in the database");
		}
		return false;

	}

	/**
	 * @param ID
	 * 
	 *            Removes a document from the database.
	 */

	@Override
	public void remove(String ID) {
		BasicDBObject document = new BasicDBObject();
		document.put("id", ID);
		users.remove(document);
	}

	/**
	 * @param ID
	 *            Returns a document (agent) from the database corresponding to
	 *            the id passed as a parameter.
	 */

	@Override
	public Agent findById(String ID) {
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("id", ID);
		DBCursor cursor = users.find(whereQuery);
		Agent c = null;
		while (cursor.hasNext()) {
			DBObject user = cursor.next();
			c = new Agent((String) user.get("name"), (String) user.get("location"), (String) user.get("email"),
					(String) user.get("id"), (int) user.get("kind"), (String) user.get("password"));
		}
		return c;
	}

	/**
	 * Returns every document (agent) in the databse.
	 */

	@Override
	public List<Agent> findAll() {

		List<Agent> allAgents = new ArrayList<>();

		DBCursor cursor = users.find();
		while (cursor.hasNext()) {
			DBObject user = cursor.next();
			Agent c = new Agent((String) user.get("name"), (String) user.get("location"), (String) user.get("email"),
					(String) user.get("id"), (int) user.get("kind"), (String) user.get("password"));
			allAgents.add(c);
		}

		return allAgents;
	}

	/**
	 * Clears the database.
	 */

	@Override
	public void cleanDatabase() {
		users.remove(new BasicDBObject());
	}

	@Override
	public void remove(Agent c) {
		remove(c.getID());
	}

	/**
	 * This method returns all the agents with the kindCode passed as a
	 * parameter
	 */
	@Override
	public List<Agent> findAllAgentByKindCode(int kind) {

		List<Agent> allAgents = null;

		try {
			new Agent("", "", "666", kind);
		} catch (IllegalArgumentException e) {
			return allAgents;
		} // this checks, if the kind is a valid number, or not

		allAgents = new ArrayList<>();

		DBCursor cursor = users.find();
		while (cursor.hasNext()) {
			DBObject user = cursor.next();
			if ((int) user.get("kind") == kind) {
				String location = null;
				if (user.get("location") != null) {
					location = (String) user.get("location");
				}
				Agent c = new Agent((String) user.get("name"), location, (String) user.get("email"),
						(String) user.get("id"), (int) user.get("kind"), (String) user.get("password"));
				allAgents.add(c);
			}
		}
		return allAgents;
	}
}
