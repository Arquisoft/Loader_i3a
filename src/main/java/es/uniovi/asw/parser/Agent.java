package es.uniovi.asw.parser;

import java.util.NoSuchElementException;

import Foundation.CSVFile;
import Foundation.URL;


public class Agent {
	private String name; // in the case of a person, it will contain both first and last name)
	private String location;
	private String email;
	private String ID;
	private String password;
	private int kindCode;
	private static final String KIND_NOT_FOUND = "KIND NOT FOUND";

	public Agent(String name, String location, String email, String ID, int kind) {
		this(name,email,ID,kind);
		setLocation(location);
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
		checkCorrectKind();
		checkCorrectID();
	}

	public Agent(String[] data) {
		this.name = data[0];
		setLocation(data[1]);// no location
		this.email = data[2];
		this.ID = data[3];
		Double k = Double.parseDouble(data[4]);
		this.kindCode = k.intValue();
		checkCorrectKind();
		checkCorrectID();
	}
	
	/**
	 * This method checks if the ID inserted is valid or not
	 */
	private void checkCorrectID() {
		if(this.ID == null){
			throw new IllegalArgumentException("The ID cannot be null");
		}else if(this.ID.isEmpty()){
			throw new IllegalArgumentException("The ID cannot be empty");
		}
	}

	/**
	 * This method checks if the number inserted by the user are correct or not
	 */
	private void checkCorrectKind() {
		if(getKind() == KIND_NOT_FOUND){
			throw new IllegalArgumentException("That kind code number does not exist");
		}
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
