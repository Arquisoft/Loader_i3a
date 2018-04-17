package es.uniovi.asw.loader_client.parser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import es.uniovi.asw.loader_client.types.Agent;
import logger.Logger;
import lombok.Getter;

public class ExcelParser {

	@Getter
	private String fileFullPath;

	@Getter
	private List<Agent> content = new ArrayList<Agent>();

	public ExcelParser(String fileFullPath) {
		Logger.addInfo("Starting parsing...");// + fileFullPath);
		setFileFullPath(fileFullPath);
		this.parse();
	}

	private void setFileFullPath(String fileFullPath) {
		String[] parts = fileFullPath.split("/");
		String filename = parts[parts.length - 1];
		String[] fileParts = filename.split("\\.");

		if (fileParts.length == 2) {// .xlsx
			String extension = fileParts[1];
			if (extension.equalsIgnoreCase("xlsx")) {
				this.fileFullPath = fileFullPath;
			} else {
				Logger.addSevere("Trying to read a not valid file with extension:", extension);
			}
		} else {
			Logger.addSevere("Trying to read a file without extension");
		}
	}

	public void parse() {

		XSSFWorkbook wb = null;
		XSSFSheet sheet;
		XSSFRow row;

		try {
			FileInputStream file = new FileInputStream(fileFullPath);
			wb = new XSSFWorkbook(OPCPackage.open(file));
			sheet = wb.getSheetAt(0);
			int rows = sheet.getPhysicalNumberOfRows();
			int cols = 5; // CAMBIADO A: name, email, id, location, kind
			for (int r = 1; r < rows; r++) {
				row = sheet.getRow(r);
				String[] data = parseRow(row, cols);
				Agent currAgent = null;
				if (data != null) {
					if (data[0] == null) {
						Logger.addWarning("Null name on row number", r);
					} else if (data[1] == null) {
						Logger.addWarning("Null email on row number", r);
					} else if (data[2] == null) {
						Logger.addWarning("Null id on row number", r);
					} else if (data[3] == null) {
						Logger.addWarning("Null location on row number", r);
					} else if (data[4] == null) {
						Logger.addWarning("Null kind on row number", r);
					} else {
						currAgent = new Agent(data);
						if (content.contains(currAgent)) {
							Logger.addWarning("Duplicated citizen on row number", r);
						} else {
							content.add(currAgent);
							Logger.addInfo("Agent added");
						}
					}
				} else {
					Logger.addWarning("Empty row n", r);
				}
			}
			wb.close();
		} catch (FileNotFoundException | NullPointerException e) {
			Logger.addSevere("No se ha encontrado el archivo solicitado");
		} catch (Exception ioe) {
			ioe.printStackTrace();
		}
		Logger.addInfo("Finish parsing...");
	}

	private String[] parseRow(XSSFRow row, int cols) throws ParseException {
		XSSFCell cell;
		String[] data = new String[cols];
		if (row != null) {
			for (int c = 0; c < cols; c++) {
				cell = row.getCell((short) c);
				if (cell != null && !cell.toString().equals("")) {
					data[c] = cell.toString();
				} else {
					data[c] = null;
				}
			}
			return data;
		}
		return null;
	}

}
