package com.stephull.projects.koreanbuildingapp.repository;

import java.util.ArrayList;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.stephull.projects.koreanbuildingapp.model.KoreanBuild;

public interface KBRepository extends MongoRepository<KoreanBuild, String> {
    
    @Query(
        value="""
        $or: [
            {
                'children.consonants.build': {
                    $ne: ''
                }
            }
        ]        
        """
            // still looking at this...
    )
    ArrayList<KoreanBuild> findAllCharacterChildren(String character);

    @Query(
        value=""
    )
    ArrayList<KoreanBuild> findAllBuildChildren(String build);
    
    @Query(
        value="{'build': ?O}"
    )
    ArrayList<KoreanBuild> retrieveDictionaryByBuild(String build); 
    
    @Query(
        value="{'build': ?O}", 
        fields="{'frequency': 1, 'appearences': 1}"
    )
    ArrayList<KoreanBuild> findStatsByBuild(String build);

}