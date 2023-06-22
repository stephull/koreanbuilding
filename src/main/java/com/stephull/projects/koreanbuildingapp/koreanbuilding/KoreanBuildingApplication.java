package com.stephull.projects.koreanbuildingapp.koreanbuilding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.stephull.projects.koreanbuildingapp.model.KB;

@SpringBootApplication
@ComponentScan(basePackages="com.stephull.projects")
public class KoreanBuildingApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(KoreanBuildingApplication.class, args);

		MongoDatabaseHandler mongoDBHandler = context.getBean(MongoDatabaseHandler.class);

		KB testBuild = new KB();
		
		mongoDBHandler.insertBuild(testBuild);
	}
}
