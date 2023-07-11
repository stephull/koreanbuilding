package com.stephull.projects.koreanbuildingapp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.stephull.projects.koreanbuildingapp.model.KoreanBuild;
import com.stephull.projects.koreanbuildingapp.model.KoreanPronunciation;

public interface KBRepository extends MongoRepository<KoreanBuild, String> {
    
    // return unicode value based on given Korean character
    String findUnicodeByBuild(String build);
    
    // return list of dictionary entries that indicate meaning of Korean character
    List<KoreanBuild> findDictionaryByBuild(String build); 

    // return romanized sound for particular character
    String findSoundKeyByBuild(String build);

    // return pronunciation property for entire Korean character
    KoreanPronunciation findPronunciationByBuild(String build);

    // return object of statistics that define appearences of Korean character
    double findAppearencesStatsByBuild(String build);

    // return object of statistics that define frequency of Korean character
    int findFrequencyStatsByBuild(String build);

    // return pronunciation rules for one singular Korean character
    boolean[] findSpecialSingularPronunciationByBuild(String build);

    // find all consonants that derive/succeed from Korean character
    List<KoreanBuild> findConsonantsByBuild(String build);

    // find all vowels that derive/succeed from Korean character
    List<KoreanBuild> findVowelsByBuild(String build);

}