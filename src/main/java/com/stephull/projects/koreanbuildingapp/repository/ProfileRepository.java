package com.stephull.projects.koreanbuildingapp.repository;

import java.util.List;

import java.lang.StringBuilder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.stephull.projects.koreanbuildingapp.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, String> {

    @Query(value="", nativeQuery=true)
    List<Profile> findProfilesBySQLQuery(
        String string, MapSqlParameterSource mapSqlParameterSource
    );

    StringBuilder queryCreator = new StringBuilder("SELECT * FROM profiles ");
    MapSqlParameterSource msps = new MapSqlParameterSource();

    /*
     * 
     */
    default List<Profile> findByUsername(String username) {
        queryCreator.append("WHERE username = :username;");
        msps.addValue("username", username);
        return findProfilesBySQLQuery(queryCreator.toString(), msps);
    }

    /*
     * 
     */
    default List<Profile> findByLevel(long level) {
        queryCreator.append("WHERE level = :level ")
            .append("ORDER BY level DESC;");
        msps.addValue("level", level);
        return findProfilesBySQLQuery(queryCreator.toString(), msps);
    }

    /*
     * 
     */
    default List<Profile> findByFluencyScore(long fluencyScore) {
        queryCreator.append("WHERE fluency_score = :fluencyScore ")
            .append("ORDER BY fluency_score DESC;");
        msps.addValue("fluency_score", fluencyScore);
        return findProfilesBySQLQuery(queryCreator.toString(), msps);
    }

    /*
     * 
     */
    default List<Profile> findByDaysActive(long daysActive) {
        queryCreator.append("WHERE days_active = :daysActive ")
            .append("ORDER BY days_active DESC;");
        msps.addValue("days_active", daysActive);
        return findProfilesBySQLQuery(queryCreator.toString(), msps);
    }

    /*
     * 
     */
    default List<Profile> findByAccountPrivacy(boolean privateAccount) {
        queryCreator.append("WHERE private_account = :privateAccount;");
        msps.addValue("private_account", privateAccount);
        return findProfilesBySQLQuery(queryCreator.toString(), msps);
    }
}
