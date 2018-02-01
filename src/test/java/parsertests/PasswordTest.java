package parsertests;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import es.uniovi.asw.parser.Citizen;
import es.uniovi.asw.parser.parserutil.PasswordGenerator;

public class PasswordTest {

	@Test
	public void testNotNull() {
		Citizen c = new Citizen("a", "b", "a@a.com", "10/10/2010", "a", "a",
				"7198791Z", "2652165165", 1234);
		Set<Citizen> census = new HashSet<Citizen>();
		census.add(c);
		PasswordGenerator.createPasswords(census);
		assertTrue(c.getPassword() != null);

		PasswordGenerator pass = new PasswordGenerator();
		assertNotNull(pass);
	}

}
