package es.uniovi.asw.parser.lettergenerators;

import es.uniovi.asw.parser.Agent;

public class ConsoleLetterGenerator implements LetterGenerator {

	@Override
	public void generatePersonalLetter(Agent c) {
		StringBuilder sb = new StringBuilder();
		sb.append("To: " + c.getEmail() + "\n");
		sb.append("Subject: Login data\n");
		sb.append("Mr/Mrs " + c.getName() + ",\n\n");
		sb.append("Your login data has been generated:\n");
		sb.append("\tUsername: " + c.getEmail() + "\n");
		sb.append("\tPassword: " + c.getPasswordNotEncrypted() + "\n");
		System.out.println(sb.toString());
	}

}
