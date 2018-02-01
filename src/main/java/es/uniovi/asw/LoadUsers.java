package es.uniovi.asw;

import es.uniovi.asw.parser.ReadList;
import es.uniovi.asw.parser.SingletonParser;

/**
 * Main application
 * 
 * @author Labra
 *
 */
public class LoadUsers {

	public static void main(String... ruta) {
		final LoadUsers runner = new LoadUsers();
		runner.run(ruta);
	}

	private void run(String... ruta) {
		if (ruta.length < 1) {
			System.err.println("Input the name of the file.");
			return;
		}

		ReadList rl = SingletonParser.getInstance().getDefaultExcelReadList();
		rl.parse(ruta[0]);
	}

}
