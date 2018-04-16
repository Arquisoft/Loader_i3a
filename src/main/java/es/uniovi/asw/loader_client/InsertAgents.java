package es.uniovi.asw.loader_client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import es.uniovi.asw.loader_client.types.Agent;
import lombok.extern.java.Log;

@Log
public class InsertAgents {

	private List<Agent> agentsToInsert = new ArrayList<Agent>();

	public InsertAgents(List<Agent> content) {
		System.out.println("Starting inserting agents... ");
		this.agentsToInsert = content;
		for (Agent a : content) {
			System.out.println(a);
		}
		this.insert();
		System.out.println("Finish inserting agents... ");
	}

	private void insert() {
		Map<String, Object> agentData;

		List<CharacterRule> rules = Arrays.asList(new CharacterRule(EnglishCharacterData.UpperCase, 1),
				new CharacterRule(EnglishCharacterData.LowerCase, 1), new CharacterRule(EnglishCharacterData.Digit, 1),
				new CharacterRule(EnglishCharacterData.Special, 1));

		for (Agent agent : agentsToInsert) {
			agentData = new HashMap<String, Object>();
			agentData.put("name", agent.getName());
			agentData.put("location", agent.getLocation());
			agentData.put("email", agent.getEmail());
			agentData.put("password", new PasswordGenerator().generatePassword(6, rules));
			agentData.put("username", agent.getID());
			agentData.put("kindCode", agent.getKindCode());

			try {
				HttpResponse<JsonNode> jsonResponse = Unirest
						.post("http://asw-i3a.guill.io:9001/agents_service/register")
						.header("Content-Type", "application/json").body(new JSONObject(agentData)).asJson();

				if (jsonResponse.getStatus() == HttpStatus.SC_CREATED) {
					log.info("Agent inserted: " + new JSONObject(agentData).toString());
					System.out.println("Agent inserted: " + new JSONObject(agentData).toString());
				} else {
					log.warning("Agent NOT inserted: " + new JSONObject(agentData).toString());
					System.out.println("Agent NOT inserted: " + new JSONObject(agentData).toString());
				}

			} catch (UnirestException e) {
				log.warning("Error sending request to the agents service " + e.getMessage());
				System.out.println(e);
			}
		}
	}

}