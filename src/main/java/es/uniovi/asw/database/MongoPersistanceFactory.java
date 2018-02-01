package es.uniovi.asw.database;

public class MongoPersistanceFactory {

	public static CitizenDao getCitizenDao() {
		return new CitizenDaoImplMongo();
	}

}
