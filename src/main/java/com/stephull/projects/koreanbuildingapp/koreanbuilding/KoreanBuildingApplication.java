package com.stephull.projects.koreanbuildingapp.koreanbuilding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@SpringBootApplication
@ComponentScan(basePackages="com.stephull.projects.koreanbuildingapp")
@EnableMongoRepositories("com.stephull.projects.koreanbuildingapp.repository")
@EnableEncryptableProperties
public class KoreanBuildingApplication {

	public static void main(String[] args) {
		SpringApplication.run(KoreanBuildingApplication.class, args);
		// always check for localhost:8080
	}
}
