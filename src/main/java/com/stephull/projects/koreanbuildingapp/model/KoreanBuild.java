package com.stephull.projects.koreanbuildingapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Optional;

@Document(collection="KoreanBuilds")
public class KoreanBuild {

    @Id
    private String id;

    private String character;
    private String build;
    private boolean complete;
    private KoreanSound sound;
    private KoreanBuildStats stats;
    private String unicode;
    private KoreanCharacterChildren children;

    public KoreanBuild() {}
    
    public KoreanBuild(
        String character,
        String build,
        boolean complete,
        KoreanSound sound,
        KoreanBuildStats stats,
        String unicode,
        KoreanCharacterChildren children
    ) {
        this.character = character;
        this.build = build;
        this.complete = complete;
        this.sound = sound;
        this.stats = stats;
        this.unicode = unicode;
        this.children = children;
    }

    public String getId() {
        return this.id;
    }

    public String getCharacter() {
        return this.character;
    }

    public void setCharacter(String newCharacter) {
        this.character = newCharacter;
    }

    public String getBuild() {
        return this.build;
    }

    public void setBuild(String newBuild) {
        this.build = newBuild;
    }

    public boolean getCompleteStatus() {
        return this.complete;
    }

    public void setCompleteStatus(boolean newCompleteStatus) {
        this.complete = newCompleteStatus;
    }

    public KoreanSound getSound() {
        return this.sound;
    }

    public void setSound(KoreanSound newSound) {
        this.sound = newSound;
    }

    public KoreanBuildStats getStats() {
        return this.stats;
    }

    public void setStats(KoreanBuildStats newStats) {
        this.stats = newStats;
    }

    public String getUnicode() {
        return this.unicode;
    }

    public void setUnicode(String newUnicode) {
        this.unicode = newUnicode;
    }

    public KoreanCharacterChildren getChildren() {
        return this.children;
    }

    public void setChildren(KoreanCharacterChildren newChildren) {
        this.children = newChildren;
    }

    @Override
    public String toString() {
        return String.format(
            """
            [
                ID=%s
                Character=%s
                Build=%s
                Complete=%b
                Sound=%s
                Stats=%s
                Unicode=%s
                Children=%s 
            ]       
            """,
            id, character, build, complete, sound.toString(), stats.toString(), unicode, children.toString()
        );
    }
}

class KoreanSound {

    private String key;
    private boolean silent;
    private boolean replace;
    private KoreanPronunciation pronunciation;

    public KoreanSound() {}

    public KoreanSound(
        String key,
        boolean silent,
        boolean replace
    ) {
        this.key = key;
        this.silent = silent;
        this.replace = replace;
    }

    public KoreanSound(
        String key,
        boolean silent,
        boolean replace,
        KoreanPronunciation pronunciation
    ) {
        this.key = key;
        this.silent = silent;
        this.replace = replace;
        this.pronunciation = pronunciation;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String newKey) {
        this.key = newKey;
    }

    public boolean getSilentStatus() {
        return this.silent;
    }

    public void setSilentStatus(boolean newSilentStatus) {
        this.silent = newSilentStatus;
    }

    public boolean getReplaceStatus() {
        return this.replace;
    }

    public void setReplaceStatus(boolean newReplaceStatus) {
        this.replace = newReplaceStatus;
    }

    public KoreanPronunciation getPronunciation() {
        return this.pronunciation;
    }

    public void setPronunciation(KoreanPronunciation newPronunciation) {
        this.pronunciation = newPronunciation;
    }

    @Override
    public String toString() {
        return String.format(
            """
            [
                Key=%s
                Silent=%b
                Replace=%b
                Pronunciation=%s
            ]        
            """,
            key, silent, replace, pronunciation.toString()
        );
    }

}

class KoreanBuildStats {

    private double frequency;
    private int appearences;

    public KoreanBuildStats() {}

    public KoreanBuildStats(
        double frequency,
        int appearences
    ) {
        this.frequency = frequency;
        this.appearences = appearences;
    }

    public double getFrequency() {
        return this.frequency;
    }

    public void setFrequency(double newFrequency) {
        this.frequency = newFrequency;
    }

    public int getAppearences() {
        return this.appearences;
    }

    public void setAppearences(int newAppearences) {
        this.appearences = newAppearences;
    }

    @Override
    public String toString() {
        return String.format(
            """
            [
                Frequency=%d
                Appearences=%d
            ]  
            """,
            frequency, appearences
        );
    }

}

class KoreanCharacterChildren {

    private Optional<ArrayList<KoreanBuild>> consonants;
    private Optional<ArrayList<KoreanBuild>> vowels;

    public KoreanCharacterChildren() {}

    public KoreanCharacterChildren(
        Optional<ArrayList<KoreanBuild>> consonants,
        Optional<ArrayList<KoreanBuild>> vowels
    ) {
        this.consonants = consonants;
        this.vowels = vowels;
    }

    public ArrayList<KoreanBuild> getConsonants() {
        return this.consonants.orElse(new ArrayList<KoreanBuild>());
    }

    public void setConsonants(Optional<ArrayList<KoreanBuild>> newConsonants) {
        this.consonants = newConsonants;
    }

    public ArrayList<KoreanBuild> getVowels() {
        return this.vowels.orElse(new ArrayList<KoreanBuild>());
    }

    public void setVowels(Optional<ArrayList<KoreanBuild>> newVowels) {
        this.vowels = newVowels;
    }

    @Override
    public String toString() {
        return String.format(
            """
            [
                Consonants=%s
                Vowels=%s
            ]      
            """,
            consonants.toString(), vowels.toString()
        );
    }

}

class KoreanPronunciationProperty {

    private String character;
    private String sound;

    public KoreanPronunciationProperty() {}

    public KoreanPronunciationProperty(
        String character,
        String sound
    ) {
        this.character = character;
        this.sound = sound;
    }

    public String getCharacter() {
        return this.character;
    }

    public void setCharacter(String newCharacter) {
        this.character = newCharacter;
    }

    public String getSound() {
        return this.sound;
    }

    public void setSound(String newSound) {
        this.sound = newSound;
    }

    @Override
    public String toString() {
        return String.format(
            """
            [
                Pronunciation character=%s
                Pronunciation sound=%s
            ]   
            """,
            character, sound
        );
    }

}

class KoreanPronunciation {

    private String type;
    private KoreanPronunciationProperty additional;
    private Optional<KoreanPronunciationProperty> replacement;
    private Optional<KoreanPronunciationProperty> combination;

    public KoreanPronunciation() {}

    public KoreanPronunciation(
        String type,
        KoreanPronunciationProperty additional,
        Optional<KoreanPronunciationProperty> replacement,
        Optional<KoreanPronunciationProperty> combination
    ) {
        this.type = type;
        this.additional = additional;
        this.replacement = replacement;
        this.combination = combination;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String newType) {
        this.type = newType;
    }

    public KoreanPronunciationProperty getAdditional() {
        return this.additional;
    }

    public void setAdditional(KoreanPronunciationProperty newAdditional) {
        this.additional = newAdditional;
    }

    public KoreanPronunciationProperty getReplacement() {
        return this.replacement.orElse(null);
    }

    public void setReplacement(Optional<KoreanPronunciationProperty> newReplacement) {
        this.replacement = newReplacement;
    }

    public KoreanPronunciationProperty getCombination() {
        return this.combination.orElse(null);
    }

    public void setCombination(Optional<KoreanPronunciationProperty> newCombination) {
        this.combination = newCombination;
    }

    @Override
    public String toString() {
        return String.format(
            """
            [
                Pronunciation type=%s
                Additional=%s
                Replacement=%s
                Combination=%s
            ]      
            """,
            type, additional.toString(), replacement.toString(), combination.toString()
        );
    }
}