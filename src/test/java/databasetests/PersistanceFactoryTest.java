package databasetests;

import static org.junit.Assert.*;

import org.junit.Test;

import es.uniovi.asw.database.MongoPersistanceFactory;

public class PersistanceFactoryTest {

	@Test
	public void test() {
		assertNotNull(MongoPersistanceFactory.getCitizenDao());
		MongoPersistanceFactory p = new MongoPersistanceFactory();
		assertNotNull(p);
	}

}
