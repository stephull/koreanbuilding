package com.stephull.projects.koreanbuildingapp.koreanbuilding;

import com.stephull.projects.koreanbuildingapp.model.KB;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class MongoDatabaseHandler {
    
    private final MongoTemplate mongoTemplate;

    public MongoDatabaseHandler(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void insertBuild(KB build) {
        mongoTemplate.insert(build);
        System.out.println(
            String.format("Korean build [%s] inserted successfully", build)
        );
    }

}
