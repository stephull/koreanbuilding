package com.stephull.projects.koreanbuildingapp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.stephull.projects.koreanbuildingapp.model.Profile;

public interface ProfileRepository extends MongoRepository<Profile, String> {
    
    List<Profile> findByUsername(String username);
    
    List<Profile> findByGameIdAndLevel(String gameId, Integer gameLevel);
    
    List<Profile> findByFluencyScore(Integer fluencyScore);

    List<Profile> findByDaysActive(Integer daysActive);
    
    List<Profile> findByContributionId(String contributionId);

}