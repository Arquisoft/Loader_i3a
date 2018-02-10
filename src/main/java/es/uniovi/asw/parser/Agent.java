package es.uniovi.asw.parser;

import java.util.NoSuchElementException;

import Foundation.CSVFile;
import Foundation.URL;

/**
 * @author Ana There are different kind of agents:physical people, entities,
 *         sensors, etc. Each type of user will be identified by a keyword like:
 *         "Person", "Entity", "Sensor"
 */
public class Agent {
	private String name; // in the case of a person, it will contain both first and last name)
	private String location;
	private String email;
	private String ID;
	private String password;
	private int kindCode;
	private String kind;
	private static final String KIND_NOT_FOUND = "KIND NOT FOUND";

	public Agent(String name, String location, String email, String ID, int kind) {

		this.name = name;
		setLocation(location);
		this.email = email;
		this.ID = ID;
		this.kindCode = kind;
	}

	public Agent(String name, String location, String email, String ID, int kind,String pass) {

		this(name,location,email,ID,kind);
		this.password = pass;
	}

	public Agent(String name, String email, String ID, int kind) {

		this.name = name;
		setLocation(null);// no location
		this.email = email;
		this.ID = ID;
		this.kindCode = kind;
	}

	public Agent(String[] data) {
		this.name = data[0];
		this.email = data[2];
		setLocation(data[1]);
		this.ID = data[3];
		Double k = Double.parseDouble(data[4]);
		this.kindCode = k.intValue();

	}
	


	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	private void setLocation(String location) {
		if (location != null) {
			this.location = location;
		} else
			location = "";

	}

	public String getLocation() {
		return location;
	}

	public String getID() {
		return ID;
	}

	public int getKindCode() {
		return kindCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID == null) ? 0 : (ID.hashCode() + Integer.valueOf(this.kindCode).hashCode()));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agent other = (Agent) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (this.hashCode() != other.hashCode())
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Agent [name=" + name + ", location=" + location + ",  email=" + email + ", ID=" + ID + ", kind="
				+ kindCode + "]";
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getKind() {
	    try {
	        return CSVFile.of( new URL( "src/main/resources/master.csv" ), ",", "id", "kind" )
		    .getRows().stream()
		    .filter( r -> r.getColumn( "id" ).equals( Integer.toString( kindCode ) ) )
		    .findAny().get().getColumn( "kind" );
	    } catch (NoSuchElementException e) {
	        return KIND_NOT_FOUND;
	    }
	}

}
