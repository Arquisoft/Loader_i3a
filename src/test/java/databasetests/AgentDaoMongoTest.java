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
		dao = new AgentDaoImplMongo();
		dao.cleanDatabase();
	}

	@Before
	public void insertCitizen() {
		dummy = new Agent("Juan Perez", "juani@uniovie.es", "56897412L", 1);
		dummy1 = new Agent("Javier Diez", "40.39N65.48W", "javier@hotmail.com", "79864152M", 2);
		dummy2 = new Agent("Carmen Sors", "20.3S59.46E", "carmen@gmail.com", "58421673D", 3);
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

		Agent aux = dao.findById(dummy.getID());
		assertNotNull(aux);

		dao.insert(dummy1);
		dao.insert(dummy2);

		citizens = dao.findAll();

		assertEquals(citizens.size(), 3);

		Agent aux1 = dao.findById(dummy1.getID());
		Agent aux2 = dao.findById(dummy2.getID());
		assertNotNull(aux1);
		assertNotNull(aux2);

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
		dao.insert(dummy); // id = 56897412L

		Agent c = dao.findById("0"); // The db does not contain anyone with this
										// id
		assertNull(c);

		c = dao.findById("56897412L");
		assertNotNull(c);
		assertEquals(dummy, c);
	}

	@Test
	public void testRemoveFromID() {
		dao.insert(dummy);
		dao.insert(dummy1);
		dao.insert(dummy2);

		List<Agent> agents = dao.findAll();

		assertEquals(3, agents.size());

		dao.remove("0"); // Does not delete anything

		assertTrue(agents.contains(dummy));
		assertTrue(agents.contains(dummy1));
		assertTrue(agents.contains(dummy2));
		assertEquals(agents.size(), 3);

		agents = dao.findAll();

		dao.remove("56897412L");

		agents = dao.findAll();

		assertEquals(2, agents.size());

		assertFalse(agents.contains(dummy));
		assertTrue(agents.contains(dummy1));
		assertTrue(agents.contains(dummy2));

		dao.remove("79864152M");

		agents = dao.findAll();

		assertEquals(1, agents.size());

		assertFalse(agents.contains(dummy));
		assertFalse(agents.contains(dummy1));
		assertTrue(agents.contains(dummy2));

	}

	@Test
	public void testRemoveAgent() {
		dao.insert(dummy);
		dao.insert(dummy1);
		dao.insert(dummy2);

		List<Agent> agents = dao.findAll();

		assertEquals(agents.size(), 3);

		Agent aux = new Agent("Diablo", "66.6N99.9W", "diablito@infierno.com", "666D", 1);

		dao.remove(aux);

		assertTrue(agents.contains(dummy));
		assertTrue(agents.contains(dummy1));
		assertTrue(agents.contains(dummy2));
		assertFalse(agents.contains(aux));
		assertEquals(agents.size(), 3);

		dao.remove(dummy);

		agents = dao.findAll();

		assertEquals(agents.size(), 2);

		assertFalse(agents.contains(dummy));
		assertTrue(agents.contains(dummy1));
		assertTrue(agents.contains(dummy2));

		dao.remove(dummy2);

		agents = dao.findAll();

		assertEquals(agents.size(), 1);

		assertFalse(agents.contains(dummy));
		assertTrue(agents.contains(dummy1));
		assertFalse(agents.contains(dummy2));
	}

	@Test
	public void testNoDuplicates() {
		dao.insert(dummy);
		dao.insert(dummy);
		dao.insert(dummy);
		dao.insert(dummy);

		List<Agent> agents = dao.findAll();

		assertEquals(agents.size(), 1);
	}

	@Test
	public void testAgentsKindCode() {
		Agent aux = new Agent("Diablo", "66.6N99.9W", "diablito@infierno.com", "666D", 1);

		dao.insert(dummy);// 1
		dao.insert(dummy1);// 2
		dao.insert(dummy2);// 3
		dao.insert(aux);// 1

		List<Agent> agents = dao.findAllAgentByKindCode(0);
		assertNull(agents);

		agents = dao.findAllAgentByKindCode(-1);
		assertNull(agents);

		agents = dao.findAllAgentByKindCode(1);
		assertNotNull(agents);
		assertTrue(agents.contains(dummy));
		assertTrue(agents.contains(aux));
		assertFalse(agents.contains(dummy1));
		assertFalse(agents.contains(dummy2));

		agents = dao.findAllAgentByKindCode(2);
		assertNotNull(agents);
		assertFalse(agents.contains(dummy));
		assertFalse(agents.contains(aux));
		assertTrue(agents.contains(dummy1));
		assertFalse(agents.contains(dummy2));

		agents = dao.findAllAgentByKindCode(3);
		assertNotNull(agents);
		assertFalse(agents.contains(dummy));
		assertFalse(agents.contains(aux));
		assertFalse(agents.contains(dummy1));
		assertTrue(agents.contains(dummy2));
	}

}
