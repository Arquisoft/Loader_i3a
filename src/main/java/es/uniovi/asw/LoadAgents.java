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
		if (parts.length > 1) {
			ReadList rl = SingletonParser.getInstance().getDefaultExcelReadList();
			ReadList rlT = SingletonParser.getInstance().getDefaultTxtReadList();
			if (parts[parts.length - 1].equalsIgnoreCase("xlsx")) {
				rl.parse(ruta[0]);
			} else if (parts[parts.length - 1].equalsIgnoreCase("txt")) {
				rlT.parse(ruta[0]);
			}else{
				System.err.println("That file extension cannot be read.");
				return;
			}
		}else{
			System.err.println("The file extension is missing.");
			return;
		}

	}

}
