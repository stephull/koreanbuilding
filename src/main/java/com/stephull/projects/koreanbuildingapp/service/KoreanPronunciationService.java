package com.stephull.projects.koreanbuildingapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.mongodb.client.MongoDatabase;
import com.stephull.projects.koreanbuildingapp.model.KoreanPronunciation;
import com.stephull.projects.koreanbuildingapp.repository.KoreanPronunciationRepository;

@Service
public class KoreanPronunciationService implements DataProviderService<KoreanPronunciation> {
    
    private String mongoClientConnectionString = "";
    private String mongoClientDatabase = "KoreanBuildingApp";

    @Autowired
    private final KoreanPronunciationRepository kprepo;

    @Autowired
    private final MongoTemplate mongoTemplate;

    public KoreanPronunciationService(
        KoreanPronunciationRepository kprepo,
        MongoTemplate mongoTemplate
    ) {
        this.kprepo = kprepo;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public boolean isCollectionEmpty() {
        return false;
    }

    @Override
    public void addObjectToCollection(MongoDatabase database, KoreanPronunciation object) {
    }

    @Override
    public void executeAllDocumentCreation() {
    }

}
