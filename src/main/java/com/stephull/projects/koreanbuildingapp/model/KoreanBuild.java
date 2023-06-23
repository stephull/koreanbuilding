package com.stephull.projects.koreanbuildingapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Optional;

@Document(collection="KoreanBuilds")
public class KoreanBuild {

    @Id
    private String id;

    private KBID kbid;
    private String character;
    private String build;
    private boolean complete;
    private KoreanSound sound;
    private KoreanBuildStats stats;
    private String unicode;
    private Optional<ArrayList<KBID>> consonants;
    private Optional<ArrayList<KBID>> vowels;

    public KoreanBuild() {}
    
    public KoreanBuild(
        String character,
        String build,
        boolean complete,
        KoreanSound sound,
        KoreanBuildStats stats,
        String unicode
    ) {
        this.character = character;
        this.build = build;
        this.complete = complete;
        this.sound = sound;
        this.stats = stats;
        this.unicode = unicode;
    }

    public KoreanBuild(
        String character,
        String build,
        boolean complete,
        KoreanSound sound,
        KoreanBuildStats stats,
        String unicode,
        Optional<ArrayList<KBID>> consonants,
        Optional<ArrayList<KBID>> vowels
    ) {
        this.character = character;
        this.build = build;
        this.complete = complete;
        this.sound = sound;
        this.stats = stats;
        this.unicode = unicode;
        this.consonants = consonants;
        this.vowels = vowels;
    }

    public String getId() {
        return this.id;
    }

    public String getKbid() {
        return this.kbid.getKBId();
    }

    public void setKbId(String newKbId) {
        this.kbid.setKBId(newKbId);
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

    public ArrayList<KBID> getConsonants() {
        return this.consonants.orElse(new ArrayList<KBID>());
    }

    public void setConsonants(Optional<ArrayList<KBID>> newConsonants) {
        this.consonants = newConsonants;
    }

    public ArrayList<KBID> getVowels() {
        return this.vowels.orElse(new ArrayList<KBID>());
    }

    public void setVowels(Optional<ArrayList<KBID>> newVowels) {
        this.vowels = newVowels;
    }

    @Override
    public String toString() {
        return String.format(
            """
            [
                Official ID=%s
                Korean Build ID=%s
                Character=%s
                Build=%s
                Unicode=%s
                Complete=%b
                Sound=%s
                Stats=%s
                Consonants=%s
                Vowels=%s
            ]       
            """,
            id, kbid, character, build, unicode, complete, 
            sound.toString(), stats.toString(), 
            consonants.orElse(new ArrayList<KBID>()).toString(),
            vowels.orElse(new ArrayList<KBID>()).toString()
        );
    }
}

class KoreanSound {

    private String key;
    private boolean silent;
    private boolean replace;
    private Optional<KoreanPronunciation> pronunciation;

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
        Optional<KoreanPronunciation> pronunciation
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
        return this.pronunciation.orElse(null);
    }

    public void setPronunciation(Optional<KoreanPronunciation> newPronunciation) {
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