package com.stephull.projects.koreanbuildingapp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import com.stephull.projects.koreanbuildingapp.model.Profile;

@Component
public interface ProfileRepository extends MongoRepository<Profile, String> {
    
    List<Profile> findProfileByUsername(String username);
    
    //List<Profile> findProfileByGameIdAndLevel(String gameId, Integer gameLevel);
    
    List<Profile> findProfileByFluencyScore(Integer fluencyScore);

    List<Profile> findProfileByDaysActive(Integer daysActive);
    
    //List<Profile> findProfileByContributionId(String contributionId);

}