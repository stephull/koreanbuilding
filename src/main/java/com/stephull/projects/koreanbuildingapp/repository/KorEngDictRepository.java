package com.stephull.projects.koreanbuildingapp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.stephull.projects.koreanbuildingapp.model.AuditoryData;
import com.stephull.projects.koreanbuildingapp.model.KorEngDictionary;

public interface KorEngDictRepository extends MongoRepository<KorEngDictionary, String> {
    
    // find all English translations for Korean words
    List<String> findTranslationByQuery(String query);

    // find all potential Sino-Korean characters that coincide with Korean word
    List<String> findSinoOriginsByQuery(String query);

    // find full definitions from Wiktionary based on query
    List<KorEngDictionary> findDefinitionsByQuery(String query);

    // find potential sound files that give pronunciation
    List<AuditoryData> findAuditoryDataByQuery(String query);
}
