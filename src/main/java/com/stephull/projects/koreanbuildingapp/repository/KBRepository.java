package com.stephull.projects.koreanbuildingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.stephull.projects.koreanbuildingapp.model.KB;

import java.util.List;

import java.lang.StringBuilder;

@Repository
public interface KBRepository extends JpaRepository<KB, String> {

    @Query(value="", nativeQuery=true)
    List<KB> findKoreanBuildsBySQLQuery(
        String string, MapSqlParameterSource mapSqlParameterSource
    );

    StringBuilder queryCreator = new StringBuilder("SELECT * FROM korean_builds ");
    MapSqlParameterSource msps = new MapSqlParameterSource();

    /*
     * 
     */
    default List<KB> findByBuildContaining(String inquiry) {
        queryCreator.append();
        msps.addValue();
        return findKoreanBuildsBySQLQuery(queryCreator.toString(), msps);
    }
    
    /*
     * 
     */
    default List<KB> findBuildChildren(String inquiry) {
        queryCreator.append();
        msps.addValue();
        return findKoreanBuildsBySQLQuery(queryCreator.toString(), msps);
    }
    
    /*
     * 
     */
    default List<KB> retrieveDictionaryByBuild(String inquiry) {
        queryCreator.append();
        msps.addValue();
        return findKoreanBuildsBySQLQuery(queryCreator.toString(), msps);
    }
    
    /*
     * 
     */
    default List<KB> findStatisticsByBuild(String inquiry) {
        queryCreator.append();
        msps.addValue();
        return findKoreanBuildsBySQLQuery(queryCreator.toString(), msps);
    }
}

// ::: NOTES :::

// findBuildChildren(String inquiry)
// return list of korean consonants or vowels that exist from given inquiry

// retrieveDictionaryBuild(String inquiry)
// return list of korean words/letters that give context to user based on given inquiry