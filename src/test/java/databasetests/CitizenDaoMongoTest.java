package databasetests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.uniovi.asw.database.CitizenDao;
import es.uniovi.asw.database.CitizenDaoImplMongo;
import es.uniovi.asw.parser.Citizen;

public class CitizenDaoMongoTest {

	private Citizen dummy;
	private Citizen dummy1;
	private Citizen dummy2;
	private static CitizenDao dao;

	@BeforeClass
	public static void setUp() {
		dao = new CitizenDaoImplMongo("localhost", 27017, "test", "test");
		dao.cleanDatabase();
	}

	@Before
	public void insertCitizen() {
		dummy = new Citizen("a", "b", "a@a.com", "10/10/2010", "a", "a",
				"123456789Z", "132456789", 1234);
		dummy1 = new Citizen("a", "b", "b@a.com", "10/10/2010", "a", "a", "2",
				"132456789", 1234);
		dummy2 = new Citizen("a", "b", "c@a.com", "10/10/2010", "a", "a", "3",
				"132456789", 1234);
	}

	@After
	public void deleteCitizens() {
		dao.cleanDatabase();
	}

	@Test
	public void testInsert() {

		dao.insert(dummy);
		List<Citizen> citizens = dao.findAll();

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

		List<Citizen> citizens = dao.findAll();

		assertEquals(citizens.size(), 3);

		assertTrue(citizens.contains(dummy));
		assertTrue(citizens.contains(dummy1));
		assertTrue(citizens.contains(dummy2));
	}

	@Test
	public void testFindById() {
		dao.insert(dummy);

		Citizen c = dao.findById("1");

		assertNull(c);

		c = dao.findById("123456789Z");
		assertNotNull(c);
		assertEquals(dummy, c);
	}

	@Test
	public void testRemove() {
		dao.insert(dummy);
		dao.insert(dummy1);
		dao.insert(dummy2);

		List<Citizen> citizens = dao.findAll();

		assertEquals(citizens.size(), 3);

		dao.remove("1");

		assertTrue(citizens.contains(dummy));
		assertTrue(citizens.contains(dummy1));
		assertTrue(citizens.contains(dummy2));

		citizens = dao.findAll();

		assertEquals(citizens.size(), 3);

		assertTrue(citizens.contains(dummy));
		assertTrue(citizens.contains(dummy1));
		assertTrue(citizens.contains(dummy2));

		dao.remove("2");

		citizens = dao.findAll();

		assertEquals(citizens.size(), 2);

		assertTrue(citizens.contains(dummy));
		assertFalse(citizens.contains(dummy1));
		assertTrue(citizens.contains(dummy2));

		dao.remove("3");

		citizens = dao.findAll();

		assertEquals(citizens.size(), 1);

		assertTrue(citizens.contains(dummy));
		assertFalse(citizens.contains(dummy1));
		assertFalse(citizens.contains(dummy2));

	}

	@Test
	public void testNoDuplicates() {
		dao.insert(dummy);
		dao.insert(dummy);
		dao.insert(dummy);
		dao.insert(dummy);

		List<Citizen> citizens = dao.findAll();

		assertEquals(citizens.size(), 1);
	}

}
