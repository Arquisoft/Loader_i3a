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
import lombok.Getter;
import lombok.extern.java.Log;

@Log
public class ExcelParser<T> {

    @Getter
    private String fileFullPath;

    @Getter
    private List<Agent> content = new ArrayList<Agent>();

    public ExcelParser(String fileFullPath) {
	System.out.println("Starting parsing... " + fileFullPath);
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
		log.info("Trying to read a not valid file with extension: " + extension);
		System.err.println("That file extension cannot be read.");
	    }
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

	    int cols = 5; // CAMBIADO A: name, location, email, id, kind
	    
	    for (int r = 1; r < rows; r++) {
		row = sheet.getRow(r);

		String[] data = parseRow(row, cols);

		Agent currAgent = null;

		if (data != null) {

		    if (data[0] == null) {
			log.warning("Null name on row number " + r);
		    } else if (data[2] == null) {
			log.warning("Null email on row number " + r);
		    } else if (data[3] == null) {
			log.warning("Null id on row number " + r);
		    } else if (data[1] == null) {
			log.warning("Null location on row number " + r);
		    } else if (data[4] == null) {
			log.warning("Null kind on row number " + r);
		    } else {

			currAgent = new Agent(data);
			
			if (content.contains(currAgent)) {
			    log.warning("Duplicated citizen on row number " + r);
			} else {
			    content.add(currAgent);
			}

		    }
		} else {
		    log.warning("Empty row n " + r);
		}

	    }

	    wb.close();
	} catch (FileNotFoundException e) {
	    log.warning("No se ha encontrado el archivo solicitado " + e);
	}

	catch (Exception ioe) {
	    ioe.printStackTrace();
	}
	
	System.out.println("Finish parsing... " + fileFullPath);
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
