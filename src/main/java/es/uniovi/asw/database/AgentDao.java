package es.uniovi.asw.database;

import java.util.List;

import es.uniovi.asw.parser.Agent;


public interface AgentDao {

	boolean insert(Agent c);

	Agent findById(String ID);

	void remove(String ID);

	List<Agent> findAll();

	void cleanDatabase();
}
