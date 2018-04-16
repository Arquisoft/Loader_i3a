package es.uniovi.asw.loader_client;

import es.uniovi.asw.loader_client.parser.ExcelParser;
import es.uniovi.asw.loader_client.types.Agent;

public class Application {

	/**
	 * Starts the application.
	 * 
	 * @param args
	 *            the first argument is expected to be the path to the excel
	 *            file
	 */
	public static void main(String... args) {

		if (args.length < 1) {
			System.err.println("Input the name of the file.");
			return;
		}

		new InsertAgents(new ExcelParser<Agent>(args[0]).getContent());
	}
}
