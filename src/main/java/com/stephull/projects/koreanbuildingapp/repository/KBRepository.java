package com.stephull.projects.koreanbuildingapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.stephull.projects.koreanbuildingapp.model.KoreanBuild;
import com.stephull.projects.koreanbuildingapp.model.KoreanPronunciation;

public interface KBRepository extends MongoRepository<KoreanBuild, String> {
    
    List<KoreanBuild> findAllCharacterChildren(String character);

    List<KoreanBuild> findAllBuildChildren(String build);
    
    List<KoreanBuild> findDictionaryByBuild(String build); 
    
    List<KoreanBuild> findStatsByBuild(String build);

    String findSoundKeyByBuild(String build);

    Optional<KoreanPronunciation> findPronunciationByBuild(String build);

    double findAppearencesStatsByBuild(String build);

    int findFrequencyStatsByBuild(String build);

    boolean[] findSpecialSingularPronunciationByBuild(String build);

    Optional<List<KoreanBuild>> findConsonantsByBuild(String build);

    Optional<List<KoreanBuild>> findVowelsByBuild(String build);

}