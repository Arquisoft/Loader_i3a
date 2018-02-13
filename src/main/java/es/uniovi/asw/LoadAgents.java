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

		ReadList rl = null;
		String[] parts = ruta[0].split("/");
		String filename = parts[parts.length - 1];
		String[] fileParts = filename.split("\\.");
		if (fileParts.length == 2) {// .xlsx || .txt
			String extension = fileParts[1];
			if (extension.equalsIgnoreCase("xlsx")) {
				rl = SingletonParser.getInstance().getDefaultExcelReadList();
			} else if (extension.equalsIgnoreCase("txt")) {
				rl = SingletonParser.getInstance().getDefaultTxtReadList();
			} else {
				System.err.println("That file extension cannot be read.");
				return;
			}
		}else {
			System.err.println("The file extension is missing.");
			return;
		}
		rl.parse(ruta[0]);
	}

}
