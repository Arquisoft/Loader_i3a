package logger;

import org.slf4j.LoggerFactory;

public class Logger {

	private static org.slf4j.Logger log = LoggerFactory.getLogger(Logger.class.getName());

	private Logger() {
	}

	public static org.slf4j.Logger getInstance() {
		return log;
	}

	public static void addWarning(String... message) {
		log.warn(convertIntoASingleString(message));
	}

	public static void addWarning(String message, int row) {
		addWarning(message, String.valueOf(row));
	}

	public static void addSevere(String... message) {
		log.error(convertIntoASingleString(message));
	}

	public static void addInfo(String... message) {
		log.info(convertIntoASingleString(message));
	}

	private static String convertIntoASingleString(String... message) {
		String result = "";
		for (int i = 0; i < message.length; i++) {
			result += message[i];
			if (i < message.length - 1) {
				result += " ";
			}
		}
		return result;
	}
}
