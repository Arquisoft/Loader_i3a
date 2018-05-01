package es.uniovi.asw.loader_client.types;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AgentTest {

	@Test
	public void testCreation() {
		String[] data = { "Juan", "juan@example.es", "56894715L", "", "", "1" };
		Agent a = new Agent(data);
		assertNotNull(a);
		assertTrue(a.toString().equals(
				"Agent(name=Juan, location=, , email=juan@example.es, ID=56894715L, password=null, kindCode=1)"));

		String[] data2 = { "Pepe", "pepe@example.es", "23568974L", "-12.56", "58.26", "2" };
		Agent a2 = new Agent(data2);
		assertTrue(a2.toString().equals(
				"Agent(name=Pepe, location=-12.56, 58.26, email=pepe@example.es, ID=23568974L, password=null, kindCode=2)"));
		assertFalse(a.equals(a2));
		assertTrue(a.canEqual(a2));
		assertFalse(a.hashCode() == a2.hashCode());

		a = a2;
		assertTrue(a.equals(a2));
		assertTrue(a.hashCode() == a2.hashCode());

	}
}
