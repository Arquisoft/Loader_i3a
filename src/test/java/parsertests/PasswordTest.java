package parsertests;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import es.uniovi.asw.parser.Agent;
import es.uniovi.asw.parser.parserutil.PasswordGenerator;

public class PasswordTest {

	@Test
	public void testNotNull() {
		Agent c = new Agent("a", "b", "a@a.com", "10/10/2010", "a", "a",
				"7198791Z", "2652165165", 1234);
		Set<Agent> census = new HashSet<Agent>();
		census.add(c);
		PasswordGenerator.createPasswords(census);
		assertTrue(c.getPassword() != null);

		PasswordGenerator pass = new PasswordGenerator();
		assertNotNull(pass);
	}

}
