package es.uniovi.asw.parser.readers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.HashSet;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import es.uniovi.asw.parser.Agent;
import es.uniovi.asw.parser.lettergenerators.LetterGenerator;

public class ExcelReadList extends AbstractReadList {

	public ExcelReadList() {
		super();
	}

	public ExcelReadList(LetterGenerator letterGenerator) {
		super(letterGenerator);
	}

	@Override
	public void doParse(String ruta) {

		XSSFWorkbook wb = null;
		XSSFSheet sheet;
		XSSFRow row;

		try {
			FileInputStream file = new FileInputStream(ruta);

			wb = new XSSFWorkbook(OPCPackage.open(file));
			sheet = wb.getSheetAt(0);
			census = new HashSet<Agent>();

			int rows = sheet.getPhysicalNumberOfRows();

			int cols = 5; // CAMBIADO A: name, location, email, id, kind

			for (int r = 1; r < rows; r++) {
				row = sheet.getRow(r);

				String[] data = parseRow(row, cols);

				Agent cit = null;

				if (data != null) {

					if (data[0] == null) {
						wReport.report("Null name on row number " + r, ruta);
					} else if (data[2] == null) {
						wReport.report("Null email on row number " + r, ruta);
					} else if (data[3] == null) {
						wReport.report("Null id on row number " + r, ruta);
					} else if (data[1] == null) {
						wReport.report("Null location on row number " + r, ruta);
					} else if (data[4] == null) {
						wReport.report("Null kind on row number " + r, ruta);
					} else {

						cit = new Agent(data);
						if (census.contains(cit)) {
							wReport.report("Duplicated citizen on row number " + r, ruta);
						} else {
							census.add(cit);
						}

					}
				} else {
					wReport.report("Empty row nº" + r, ruta);
				}

			}

			wb.close();
		} catch (FileNotFoundException e) {
			wReport.report(e, "No se ha encontrado el archivo solicitado");
		}

		catch (Exception ioe) {
			ioe.printStackTrace();
		}
	}

	private String[] parseRow(XSSFRow row, int cols) throws ParseException {
		XSSFCell cell;
		String[] data = new String[cols];

		if (row != null) {
			for (int c = 0; c < cols; c++) {
				cell = row.getCell((short) c);
				if (cell != null && !cell.toString().equals("")) {
					data[c] = cell.toString();
				}
			}
			return data;
		}
		return null;
	}

}
