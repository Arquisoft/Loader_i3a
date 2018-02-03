package es.uniovi.asw.parser.lettergenerators;

import es.uniovi.asw.parser.Agent;

/**
 * 
 * @author Oriol
 * Default generator
 */
public class ConsoleLetterGenerator implements LetterGenerator{

	@Override
	public void generatePersonalLetter(Agent c) {
		StringBuilder sb = new StringBuilder();
		sb.append("To: "+c.getEmail()+"\n");
		sb.append("Subject: Login data\n");
		sb.append("Mr/Mrs "+ c.getName() +",\n\n");
		sb.append("Your login data has been generated:\n");
		sb.append("\tUsername: "+c.getID()+"\n");
		sb.append("\tPassword: "+c.getPassword()+"\n");
		System.out.println(sb.toString());
		//System.out.println(c.getID() +" letter sent.");
	}

}
