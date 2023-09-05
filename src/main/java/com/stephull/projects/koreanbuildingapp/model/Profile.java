package com.stephull.projects.koreanbuildingapp.model;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="Profiles")
public class Profile {

    @Id 
    @Field("_id")
    private String id;

    @Field("profileId")
    private CustomID<Profile> profileId;
    
    private String username;
    private String pin;
    private int level;
    private int daysActive;
    private Optional<Integer> fluencyScore;
    private Optional<String> biography;
    private Optional<ArrayList<KoreanBuild>> buildsCompleted;
    private ProfileSettings settings;

    public Profile() {};

    public Profile(
        String username,
        String pin,
        int level,
        int daysActive,
        ProfileSettings settings
    ) {
        this.username = username;
        this.pin = pin;
        this.level = level;
        this.daysActive = daysActive;
        this.settings = settings;
    }

    public Profile(
        String username,
        String pin,
        int level,
        int daysActive,
        int fluencyScore,
        String biography,
        ArrayList<KoreanBuild> buildsCompleted,
        ProfileSettings settings
    ) {
        this.username = username;
        this.pin = pin;
        this.level = level;
        this.daysActive = daysActive;
        this.fluencyScore = Optional.ofNullable(fluencyScore);
        this.biography = Optional.ofNullable(biography);
        this.buildsCompleted = Optional.ofNullable(buildsCompleted);
        this.settings = settings;
    }

    public String getId() {
        return this.id;
    }

    public String getProfileId() {
        return this.profileId.getCustomID();
    }

    public void setProfileId(String newId) {
        this.profileId.setCustomID(newId);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String newUsername) {
        this.username = newUsername;
    }

    public String getPin() {
        return this.pin;
    }

    public void setPin(String newPin) {
        this.pin = newPin;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int newLevel) {
        this.level = newLevel;
    }

    public int getDaysActive() {
        return this.daysActive;
    }

    public void setDaysActive(int newDaysActive) {
        this.daysActive = newDaysActive;
    }

    public int getFluencyScore() {
        return (int)this.fluencyScore.orElse(0);
    }

    public void setFluencyScore(Optional<Integer> newFluencyScore) {
        this.fluencyScore = newFluencyScore;
    }

    public String getBiography() {
        return this.biography.orElse("None");
    }

    public void setBiography(Optional<String> newBiography) {
        this.biography = newBiography;
    }

    public ArrayList<KoreanBuild> getBuildsCompleted() {
        return this.buildsCompleted.orElse(new ArrayList<KoreanBuild>());
    }

    public void setBuildsCompleted(
        Optional<ArrayList<KoreanBuild>> newBuildsCompleted
    ) {
        this.buildsCompleted = newBuildsCompleted;
    }

    public ProfileSettings getProfileSettings() {
        return this.settings;
    }  

    public void setProfileSettings(ProfileSettings newSettings) {
        this.settings = newSettings;
    }

    @Override
    public String toString() {
        String ret = String.format(
            """
            [
                ID=%s
                Username=%s
                Level=%d
                Days active=%d
                Fluency score=%d
                Biography=%s
                Builds completed=%s
            ]
            """,
            id, 
            username, 
            level, 
            daysActive,
            fluencyScore,
            biography,
            buildsCompleted
        );
        return ret.indent(2);
    }
}