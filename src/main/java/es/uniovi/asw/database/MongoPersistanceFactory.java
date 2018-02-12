package es.uniovi.asw.database;

public class MongoPersistanceFactory {

	public static AgentDao getAgentDao() {
		return new AgentDaoImplMongo();
	}

}
