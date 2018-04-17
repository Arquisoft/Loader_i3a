package types;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import es.uniovi.asw.loader_client.types.Agent;

public class AgentTest {

	@Test
	public void testCreation() {
		String[] data = { "Juan", "juan@example.es", "56894715L", "", "1" };
		Agent a = new Agent(data);
		assertNotNull(a);
		assertTrue(a.toString()
				.equals("Agent(name=Juan, location=, email=juan@example.es, ID=56894715L, password=null, kindCode=1)"));

		String[] data2 = { "Pepe", "pepe@example.es", "23568974L", "56N89E", "2" };
		Agent a2 = new Agent(data2);
		assertFalse(a.equals(a2));
		assertFalse(a.hashCode() == a2.hashCode());

	}
}
