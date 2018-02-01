package parsertests;

import static org.junit.Assert.*;

import org.junit.Test;

import es.uniovi.asw.parser.Citizen;

public class CitizenTest {

	@Test
	public void testEquals() {
		Citizen dummy = new Citizen("a", "b", "a@a.com", "10/10/2010", "a", "a",
				"123456789Z", "132456789", 1234);
		Citizen dummy1 = new Citizen("b", "c", "b@a.com", "10/10/2010", "a",
				"a", "123456789Z", "132456789", 1234);
		Citizen dummy2 = new Citizen("a", "b", "b@a.com", "10/10/2010", "a",
				"a", "3", "132456789", 1234);
		Citizen dummy3 = null;
		Citizen dummy4 = new Citizen("a", "b", "b@a.com", "10/10/2010", "a",
				"a", null, "132456789", 1234);
		Citizen dummy5 = new Citizen("a", "b", "b@a.com", "10/10/2010", "a",
				"a", null, "132456789", 1234);
		Double doub = new Double(5.0);

		assertTrue(dummy.equals(dummy));
		assertTrue(dummy.equals(dummy1));
		assertFalse(dummy.equals(dummy2));
		assertFalse(dummy.equals(dummy3));
		assertFalse(dummy4.equals(dummy1));
		assertFalse(dummy.equals(doub));
		assertTrue(dummy4.equals(dummy5));
	}

}
