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
	private int kindCode;

	public Agent(String[] data) {
		this.name = data[0];
		this.email = data[1];
		this.ID = data[2];
		setLocation(data[3] + ", " + data[4]);// no location
		Double k = Double.parseDouble(data[5]);
		this.kindCode = k.intValue();
	}
}
