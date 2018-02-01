package es.uniovi.asw.reportwriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.log4j.Logger;

/**
 * 
 * WriteReport implementation. Detected errors during the parsing of the Excel
 * file or the database access are stores in a log file. This log file is
 * generated daily. The name of the file is the same as the date of generation.
 * 
 * @author Gonzalo de la Cruz Fernández - UO244583
 *
 */
public class WriteReportDefault implements WriteReport {

	private File file;
	private SimpleDateFormat filenameFormat = new SimpleDateFormat("dd-MM-yyyy",
			Locale.getDefault());
	private SimpleDateFormat reportHourFormat = new SimpleDateFormat(
			"dd-MM-yyyy HH:mm:ss", Locale.getDefault());

	private Logger log = Logger.getRootLogger();

	/**
	 * 
	 * Class constructor, creates a new log file if one does not exist for the
	 * current date.
	 * 
	 */
	public WriteReportDefault() {

		Date currentDate = new Date();
		String filename = filenameFormat.format(currentDate) + ".txt";
		this.file = new File(filename);

		try {
			if (!file.exists()) {
				file.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	/**
	 * 
	 * Method used to notify the user about every error produced when reading a
	 * certain file.
	 * 
	 * Writes into the corresponding log file the moment when the error
	 * happened, the file it was parsing and the error trace.
	 * 
	 * @param errorMessage
	 *            - Description corresponding to the error.
	 * 
	 * @param file
	 *            - File that was being parsed when the error occurred.
	 * 
	 */
	public void report(String errorMessage, String file) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(this.file,
					true));

			StringBuilder error = new StringBuilder();
			error.append("ERROR \n");
			error.append("------------------------------\n");
			error.append("Date and hour: " + reportHourFormat.format(new Date())
					+ "\n");
			error.append("Filename: " + file + "\n");
			error.append("Error: " + errorMessage + "\n\n");

			writer.append(error);
			log.info(error);

			writer.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	/**
	 * 
	 * Method called when an exception is produced during an operation in which
	 * the database is involved.
	 * 
	 * @param e
	 *            - Produced exception
	 *
	 * @param errorMessage
	 *            - Contains the description of the error
	 */
	public void report(Exception e, String errorMessage) {
		try {

			BufferedWriter writer = new BufferedWriter(new FileWriter(file,
					true));

			StringBuilder error = new StringBuilder();

			error.append("ERROR \n");
			error.append("------------------------------\n");
			error.append("Date and hour: " + reportHourFormat.format(new Date())
					+ "\n");
			error.append("Error: " + errorMessage + "\n");
			error.append("Message exception: " + e.getMessage() + "\n\n");

			writer.append(error);
			log.info(error);

			writer.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	public void logDatabaseInsertion(Object inserted) {
		log.info("NEW DATABASE INSERTION \n");
		log.info("------------------------------\n");
		log.info(inserted + "\n");
	}
}
