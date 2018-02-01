package es.uniovi.asw.parser.readers;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;

import es.uniovi.asw.parser.Citizen;
import es.uniovi.asw.parser.lettergenerators.LetterGenerator;

public class TxtReadList extends AbstractReadList {

	public TxtReadList() {
		super();
	}

	public TxtReadList(LetterGenerator letterGenerator) {
		super(letterGenerator);
	}

	@Override
	public void doParse(String ruta) {
		try {
			FileInputStream fstream = new FileInputStream(ruta);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			census = new HashSet<Citizen>();
			int r = 1;
			int cols = 9;
			while ((strLine = br.readLine()) != null) {
				String[] split = strLine.split(";");
				if (split.length == cols) {
					if (split[6].equals("")) {
						wReport.report("Null DNI on row number " + r, ruta);
					} else if (split[0].equals("")) {
						wReport.report("Null name on row number " + r, ruta);
					} else if (split[3].equals("")) {
						wReport.report("Null birth date on row number " + r, ruta);
					} else if (split[4].equals("")) {
						wReport.report("Null address on row number " + r, ruta);
					} else if (split[1].equals("")) {
						wReport.report("Null last name on row number " + r, ruta);
					} else if (split[7].equals("")) {
						wReport.report("Null NIF on row number " + r, ruta);
					} else {
						Citizen cit = new Citizen(split);
						if (census.contains(cit)) {
							wReport.report("Duplicated citizen on row number " + r, ruta);
						} else {
							census.add(cit);
						}
					}
				} else {
					wReport.report("Empty or shorter than expected or larger than expected"
							+ " row nº" + r, ruta);
				}
				r++;
			}
			in.close();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

}
