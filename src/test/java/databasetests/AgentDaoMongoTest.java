package databasetests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.uniovi.asw.database.AgentDao;
import es.uniovi.asw.database.AgentDaoImplMongo;
import es.uniovi.asw.parser.Agent;

public class AgentDaoMongoTest {

	private Agent dummy;
	private Agent dummy1;
	private Agent dummy2;
	private static AgentDao dao;

	@BeforeClass
	public static void setUp() {
		dao = new AgentDaoImplMongo(/*"localhost", 27017, "test", "test"*/);
		dao.cleanDatabase();
	}

	@Before
	public void insertCitizen() {
		dummy = new Agent("a", "b", "a@a.com", "1", 1);
		dummy1 = new Agent("a", "b", "b@a.com", "2", 2);
		dummy2 = new Agent("a", "b", "c@a.com", "3", 3);
	}

	@After
	public void deleteCitizens() {
		dao.cleanDatabase();
	}

	@Test
	public void testInsert() {

		dao.insert(dummy);
		List<Agent> citizens = dao.findAll();

		assertEquals(citizens.size(), 1);

		dao.insert(dummy1);
		dao.insert(dummy2);

		citizens = dao.findAll();

		assertEquals(citizens.size(), 3);

	}

	@Test
	public void testFindAll() {
		dao.insert(dummy);
		dao.insert(dummy2);
		dao.insert(dummy1);

		List<Agent> citizens = dao.findAll();

		assertEquals(citizens.size(), 3);

		assertTrue(citizens.contains(dummy));
		assertTrue(citizens.contains(dummy1));
		assertTrue(citizens.contains(dummy2));
	}

	@Test
	public void testFindById() {
		dao.insert(dummy); //id = 1

		Agent c = dao.findById("0"); //The db does not contain anyone with this id

		assertNull(c);

		c = dao.findById("1");
		assertNotNull(c);
		assertEquals(dummy, c);
	}

	@Test
	public void testRemove() {
		dao.insert(dummy);
		dao.insert(dummy1);
		dao.insert(dummy2);

		List<Agent> citizens = dao.findAll();

		assertEquals(citizens.size(), 3);

		dao.remove("0"); //Does not delete anything

		assertTrue(citizens.contains(dummy));
		assertTrue(citizens.contains(dummy1));
		assertTrue(citizens.contains(dummy2));

		citizens = dao.findAll();

		assertEquals(citizens.size(), 3);

		assertTrue(citizens.contains(dummy));
		assertTrue(citizens.contains(dummy1));
		assertTrue(citizens.contains(dummy2));

		dao.remove("1");

		citizens = dao.findAll();

		assertEquals(citizens.size(), 2);

		assertFalse(citizens.contains(dummy));
		assertTrue(citizens.contains(dummy1));
		assertTrue(citizens.contains(dummy2));

		dao.remove("3");

		citizens = dao.findAll();

		assertEquals(citizens.size(), 1);

		assertFalse(citizens.contains(dummy));
		assertTrue(citizens.contains(dummy1));
		assertFalse(citizens.contains(dummy2));

	}

	@Test
	public void testNoDuplicates() {
		dao.insert(dummy);
		dao.insert(dummy);
		dao.insert(dummy);
		dao.insert(dummy);

		List<Agent> citizens = dao.findAll();

		assertEquals(citizens.size(), 1);
	}

}
