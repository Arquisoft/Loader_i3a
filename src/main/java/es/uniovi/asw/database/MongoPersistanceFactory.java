package es.uniovi.asw.database;

public class MongoPersistanceFactory {

	public static AgentDao getCitizenDao() {
		return new CitizenDaoImplMongo();
	}

}
