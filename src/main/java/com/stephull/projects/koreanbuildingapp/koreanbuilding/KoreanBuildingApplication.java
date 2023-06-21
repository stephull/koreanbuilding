package com.stephull.projects.koreanbuildingapp.koreanbuilding;

import java.util.Map;
import java.util.HashMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.ConfigurableApplicationContext;

import com.google.gson.JsonObject;

@SpringBootApplication
public class KoreanBuildingApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(KoreanBuildingApplication.class, args);
		
		JsonToSqlConverter converter = context.getBean(JsonToSqlConverter.class);
		Map<String, JsonObject> allJsonObjects = getJsonData();
		converter.convertJsonToSql(allJsonObjects);

		context.close();
	}

	private static Map<String, JsonObject> getJsonData() {
		JsonObject kbJson = getKBJson();
		JsonObject koreanWordsJson = getKoreanWordsJson();
		JsonObject profileJson = getProfilesJson();

		Map<String, JsonObject> jsonDataMap = new HashMap<>();
		jsonDataMap.put("korean_builds", kbJson);
		jsonDataMap.put("korean_words", koreanWordsJson);
		jsonDataMap.put("profiles", profileJson);

		return jsonDataMap;
	}

	private static JsonObject getKBJson() {
		return new JsonObject();
	}

	private static JsonObject getKoreanWordsJson() {
		return new JsonObject();
	}

	private static JsonObject getProfilesJson() {
		return new JsonObject();
	}

}
