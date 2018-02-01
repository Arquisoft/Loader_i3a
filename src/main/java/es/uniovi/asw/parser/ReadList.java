package es.uniovi.asw.parser;

import java.util.Set;

/**
 * @author Oriol
 * 
 */
public interface ReadList {
	Set<Citizen> parse(String ruta);
}
