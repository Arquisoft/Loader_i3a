package es.uniovi.asw.parser.parserutil;

import java.security.SecureRandom;
import java.util.Set;

import es.uniovi.asw.parser.Citizen;

public class PasswordGenerator {
	private final static String passCharacters = "0123456789ABCDEFGHIJKLMNOP"
			+ "QRSTUVWXYZabcdefghijklmnopqrstuvwxyz$-_¡!?¿@";
	private final static int passLength = 12;
	
	public static void createPasswords(Set<Citizen> census) {
		SecureRandom rnd = new SecureRandom();
		StringBuilder sb = new StringBuilder(passLength);
		for(Citizen c: census) {
			for (int i = 0; i < passLength; i++) {
				sb.append(passCharacters
						.charAt(rnd.nextInt(passCharacters.length())));
			}
			c.setPassword(sb.toString());
		}
	}

}
