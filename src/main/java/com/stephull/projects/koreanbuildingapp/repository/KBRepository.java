package com.stephull.projects.koreanbuildingapp.repository;

import java.util.ArrayList;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.stephull.projects.koreanbuildingapp.model.KoreanBuild;

public interface KBRepository extends MongoRepository<KoreanBuild, String> {
    
    ArrayList<KoreanBuild> findAllCharacterChildren(String character);

    ArrayList<KoreanBuild> findAllBuildChildren(String build);
    
    ArrayList<KoreanBuild> findDictionaryByBuild(String build); 
    
    ArrayList<KoreanBuild> findStatsByBuild(String build);

}