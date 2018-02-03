package es.uniovi.asw.database;

import java.util.List;

import es.uniovi.asw.parser.Agent;

/**
 * Interface for the methods that the DAO must accomplish
 * 
 * @author Gonzalo de la Cruz Fernández - UO244583
 *
 */
public interface AgentDao {

	boolean insert(Agent c);

	Agent findById(String ID);

	void remove(String ID);

	List<Agent> findAll();

	void cleanDatabase();
}
