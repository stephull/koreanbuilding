package com.stephull.projects.koreanbuildingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.stephull.projects.koreanbuildingapp.model.KoreanBuild;

import java.util.List;

import java.lang.StringBuilder;

public interface KBRepository extends JpaRepository<KoreanBuild, String> {

    @Query(value="", nativeQuery=true)
    List<KoreanBuild> findKoreanBuildsBySQLQuery(
        String string, MapSqlParameterSource mapSqlParameterSource
    );

    StringBuilder queryCreator = new StringBuilder("SELECT * FROM korean_builds ");
    MapSqlParameterSource msps = new MapSqlParameterSource();

    /*
     * 
     */
    default List<KoreanBuild> findByBuildContaining(String inquiry) {
        //queryCreator.append();
        //msps.addValue();
        return findKoreanBuildsBySQLQuery(queryCreator.toString(), msps);
    }
    
    /*
     * 
     */
    default List<KoreanBuild> findBuildChildren(String inquiry) {
        //queryCreator.append();
        //msps.addValue();
        return findKoreanBuildsBySQLQuery(queryCreator.toString(), msps);
    }
    
    /*
     * 
     */
    default List<KoreanBuild> retrieveDictionaryByBuild(String inquiry) {
        //queryCreator.append();
        //msps.addValue();
        return findKoreanBuildsBySQLQuery(queryCreator.toString(), msps);
    }
    
    /*
     * 
     */
    default List<KoreanBuild> findStatisticsByBuild(String inquiry) {
        //queryCreator.append();
        //msps.addValue();
        return findKoreanBuildsBySQLQuery(queryCreator.toString(), msps);
    }
}

// ::: NOTES :::

// findBuildChildren(String inquiry)
// return list of korean consonants or vowels that exist from given inquiry

// retrieveDictionaryBuild(String inquiry)
// return list of korean words/letters that give context to user based on given inquiry