package es.uniovi.asw.parser.lettergenerators;

import es.uniovi.asw.parser.Citizen;

/**
 * 
 * @author Oriol
 * Default generator
 */
public class ConsoleLetterGenerator implements LetterGenerator{

	@Override
	public void generatePersonalLetter(Citizen c) {
		/*StringBuilder sb = new StringBuilder();
		sb.append("To: "+c.getEmail()+"\n");
		sb.append("Subject: Login data\n");
		sb.append("Mr/Mrs "+ c.getName() +" "+ c.getlastName()+",\n\n");
		sb.append("Your login data has been generated:\n");
		sb.append("\tUsername: "+c.getEmail()+"\n");
		sb.append("\tPassword: "+c.getPassword()+"\n");*/
		System.out.println(c.getID() +" letter sent.");
	}

}
