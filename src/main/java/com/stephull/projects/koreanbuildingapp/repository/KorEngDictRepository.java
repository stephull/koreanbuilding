package com.stephull.projects.koreanbuildingapp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import com.stephull.projects.koreanbuildingapp.model.DictionaryDefinition;
import com.stephull.projects.koreanbuildingapp.model.KorEngDictionary;
import com.stephull.projects.koreanbuildingapp.model.KoreanBuild;

@Component
public interface KorEngDictRepository extends MongoRepository<KorEngDictionary, String> {
    
    List<DictionaryDefinition> findDefinitionsByEntry(String entry);

    List<List<KoreanBuild>> findRelatedBuildsByEntry(String entry);
}
