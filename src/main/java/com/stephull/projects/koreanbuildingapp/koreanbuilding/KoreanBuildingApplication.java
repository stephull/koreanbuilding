package com.stephull.projects.koreanbuildingapp.koreanbuilding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan({"com.stephull.projects.koreanbuildingapp"})
@EnableMongoRepositories("com.stephull.projects.koreanbuildingapp.repository")
public class KoreanBuildingApplication {

	public static void main(String[] args) {
		SpringApplication.run(KoreanBuildingApplication.class, args);
	}
}
