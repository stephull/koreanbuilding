package com.stephull.projects.koreanbuildingapp.repository;

import java.util.ArrayList;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.stephull.projects.koreanbuildingapp.model.Profile;

public interface ProfileRepository extends MongoRepository<Profile, String> {
    
    @Query(value="{'username': ?O}")
    ArrayList<Profile> findByUsername(String username);
    
    @Query(value="{'level': ?O}")
    ArrayList<Profile> findByLevel(int level);
    
    @Query(value="{'fluencyScore': ?O}")
    ArrayList<Profile> findByFluencyScore(Integer fluencyScore);
    
    @Query(value="{'daysActive': ?O}")
    ArrayList<Profile> findByDaysActive(int daysActive);
    
    @Query(value="{'privateAccount': ?O}")
    ArrayList<Profile> findByAccountPrivacy(boolean privateAccount);

}