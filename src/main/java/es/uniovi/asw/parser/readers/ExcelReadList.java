package es.uniovi.asw.parser.readers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import es.uniovi.asw.parser.Citizen;
import es.uniovi.asw.parser.lettergenerators.LetterGenerator;

/**
 * @author Oriol. Excel parser.
 */
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
			census = new HashSet<Citizen>();

			int rows = sheet.getPhysicalNumberOfRows();

			int cols = 9; // Nombre/Apellidos/Email/Fecha
							// nacimiento/Dirección/Nacionalidad/DNI/NIF/Polling
							// code

			for (int r = 1; r < rows; r++) {
				row = sheet.getRow(r);

				String[] data = parseRow(row, cols);

				Citizen cit = null;

				if (data != null) {

					if (data[6] == null) {
						wReport.report("Null DNI on row number " + r, ruta);
					} else if (data[0] == null) {
						wReport.report("Null name on row number " + r, ruta);
					} else if (data[3] == null) {
						wReport.report("Null birth date on row number " + r, ruta);
					} else if (data[4] == null) {
						wReport.report("Null address on row number " + r, ruta);
					} else if (data[1] == null) {
						wReport.report("Null last name on row number " + r, ruta);
					} else if (data[7] == null) {
						wReport.report("Null NIF on row number " + r, ruta);
					} else {
						cit = new Citizen(data);
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

	@SuppressWarnings("deprecation")
	private String[] parseRow(XSSFRow row, int cols) throws ParseException {
		XSSFCell cell;
		String[] data = new String[cols];

		if (row != null) {
			for (int c = 0; c < cols; c++) {
				cell = row.getCell((short) c);
				if (cell != null && !cell.toString().equals("")) {
					if (cell.getCellTypeEnum() == CellType.NUMERIC 
							&& DateUtil.isCellDateFormatted(cell)) {
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						data[c] = sdf.format(cell.getDateCellValue());
					} else {
						data[c] = cell.toString();
					}
				}
			}
			return data;
		}
		return null;
	}

}
