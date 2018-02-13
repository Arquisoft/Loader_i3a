package es.uniovi.asw;

import es.uniovi.asw.parser.ReadList;
import es.uniovi.asw.parser.SingletonParser;

/**
 * Main application
 * 
 * @author Labra
 *
 */
public class LoadAgents {

	public static void main(String... ruta) {
		final LoadAgents runner = new LoadAgents();
		runner.run(ruta);
	}

	private void run(String... ruta) {
		if (ruta.length < 1) {
			System.err.println("Input the name of the file.");
			return;
		}

		String[] parts = ruta[0].split(".");
		ReadList rl = null;
		if (parts[parts.length - 1].equalsIgnoreCase("xlsx")) {
			rl = SingletonParser.getInstance().getDefaultExcelReadList();
		} else if (parts[parts.length - 1].equalsIgnoreCase("txt")) {
			rl = SingletonParser.getInstance().getDefaultTxtReadList();
		}
		rl.parse(ruta[0]);
	}

}
