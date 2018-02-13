package parsertests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import es.uniovi.asw.parser.Agent;
import es.uniovi.asw.parser.parserutil.PasswordGenerator;

public class PasswordTest {

	@Test
	public void testNotNull() {
		Agent c = new Agent("a b", "", "a@a.com", "7198791Z", 1);
		Set<Agent> census = new HashSet<Agent>();
		census.add(c);
		PasswordGenerator.createPasswords(census);
		assertTrue(c.getPassword() != null);

		PasswordGenerator pass = new PasswordGenerator();
		assertNotNull(pass);
	}

	@Test
	public void testNotRepeated() {
		Agent c = new Agent("a b", "", "a@a.com", "7198791Z", 1);
		Agent c2 = new Agent("Juan Alvarez", "juan@gmail.com", "23568974K", 2);
		Agent c3 = new Agent("Pepito Perez", "pepito@gmail.com", "58963214L", 1);
		Agent c4 = new Agent("Carlos Jimenez", "carlitos@gmail.com", "79568412D", 1);
		Set<Agent> census = new HashSet<Agent>();
		census.add(c);
		census.add(c2);
		census.add(c3);
		census.add(c4);
		PasswordGenerator.createPasswords(census);
		assertFalse(c.getPassword() == c2.getPassword());
		assertFalse(c.getPassword() == c3.getPassword());
		assertFalse(c.getPassword() == c4.getPassword());

		assertFalse(c2.getPassword() == c3.getPassword());
		assertFalse(c2.getPassword() == c4.getPassword());

		assertFalse(c3.getPassword() == c4.getPassword());
	}

}
