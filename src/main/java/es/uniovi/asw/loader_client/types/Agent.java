package es.uniovi.asw.loader_client.types;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Agent {
	private String name;
	private String location;
	private String email;
	private String ID;
	private String password;
	private String passwordNotEncrypted;
	private int kindCode;

	public Agent(String[] data) {
		this.name = data[0];
		setLocation(data[1]);// no location
		this.email = data[2];
		this.ID = data[3];
		Double k = Double.parseDouble(data[4]);
		this.kindCode = k.intValue();
	}
}
