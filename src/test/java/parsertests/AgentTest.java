package parsertests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import es.uniovi.asw.parser.Agent;

public class AgentTest {

	@Test
	public void testEquals() {
		Agent dummy = new Agent("a", "30N50.5E", "b@a.com", "132456789", 1);
		Agent dummy1 = new Agent("a", "30N50.5E", "b@a.com", "132456789", 1);
		Agent dummy2 = new Agent("a", "b@a.com", "132456789", 2);
		Agent dummy3 = null;
		Agent dummy4 = new Agent("b", "c", "b@a.com", "1324567239", 2);
		Agent dummy5 = new Agent("b", "c", "b@a.com", "1324567239", 2);
		Double doub = new Double(5.0);

		assertTrue(dummy.equals(dummy));
		assertTrue(dummy.equals(dummy1));
		assertFalse(dummy.equals(dummy2));
		assertFalse(dummy.equals(dummy3));
		assertFalse(dummy4.equals(dummy1));
		assertFalse(dummy.equals(doub));
		assertTrue(dummy4.equals(dummy5));
	}

	@Test
	public void testKindCode() {
		try {
			new Agent("Juan Alvarez", "juan@gmail.com", "23568974K", 4);
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().equals("That kind code number does not exist"));
		}

		try {
			new Agent("Pepito Perez", "pepito@gmail.com", "58963214L", -1);
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().equals("That kind code number does not exist"));
		}
	}

	@Test
	public void testEmptyID() {
		try {
			new Agent("Juan Alvarez", "juan@gmail.com", "", 2);
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().equals("The ID cannot be empty"));
		}

		try {
			new Agent("Pepito Perez", "pepito@gmail.com", null, 1);
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().equals("The ID cannot be null"));
		}
	}

}
