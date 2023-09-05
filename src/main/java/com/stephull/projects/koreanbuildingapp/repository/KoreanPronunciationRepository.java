package com.stephull.projects.koreanbuildingapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import com.stephull.projects.koreanbuildingapp.model.KoreanPronunciation;

@Component
public interface KoreanPronunciationRepository extends MongoRepository<KoreanPronunciation, String>{
    
    //

}
