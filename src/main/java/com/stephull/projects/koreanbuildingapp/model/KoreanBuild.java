package com.stephull.projects.koreanbuildingapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Optional;

@Document(collection="KoreanBuilds")
public class KoreanBuild {

    @Id
    private String id;

    private CustomID<KoreanBuild> kbid;
    private KoreanSpeech speech;
    private String build;
    private boolean complete;

    private Optional<KoreanBuildSound> sound;
    private Optional<KoreanBuildStats> stats;
    private Optional<String> unicode;

    private Optional<ArrayList<CustomID<KoreanBuild>>> consonants;
    private Optional<ArrayList<CustomID<KoreanBuild>>> vowels;
    private Optional<ArrayList<CustomID<KorEngDictionary>>> dictionary;

    public KoreanBuild() {}
    
    // for demo purposes
    public KoreanBuild(
        KoreanSpeech speech,
        String build,
        boolean complete
    ) {
        this.speech = speech;
        this.build = build;
        this.complete = complete;
    }

    // normal constructor
    public KoreanBuild(
        KoreanSpeech speech,
        String build,
        boolean complete,
        KoreanBuildSound sound,
        KoreanBuildStats stats,
        String unicode
    ) {
        this.speech = speech;
        this.build = build;
        this.complete = complete;
        this.sound = Optional.ofNullable(sound);
        this.stats = Optional.ofNullable(stats);
        this.unicode = Optional.ofNullable(unicode);
    }

    // with true-optional parameters
    public KoreanBuild(
        KoreanSpeech speech,
        String build,
        boolean complete,
        KoreanBuildSound sound,
        KoreanBuildStats stats,
        String unicode,
        ArrayList<CustomID<KoreanBuild>> consonants,
        ArrayList<CustomID<KoreanBuild>> vowels,
        ArrayList<CustomID<KorEngDictionary>> dictionary
    ) {
        this.speech = speech;
        this.build = build;
        this.complete = complete;
        this.sound = Optional.ofNullable(sound);
        this.stats = Optional.ofNullable(stats);
        this.unicode = Optional.ofNullable(unicode);
        this.consonants = Optional.ofNullable(consonants);
        this.vowels = Optional.ofNullable(vowels);
        this.dictionary = Optional.ofNullable(dictionary);
    }

    public String getId() {
        return this.id;
    }

    public String getKbid() {
        return this.kbid.getCustomID();
    }

    public void setKbId(String newKbId) {
        this.kbid.setCustomID(newKbId);
    }

    public KoreanSpeech getSpeech() {
        return this.speech;
    }

    public void setSpeech(KoreanSpeech newSpeech) {
        this.speech = newSpeech;
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

    public KoreanBuildSound getSound() {
        return this.sound.orElse(null);
    }

    public void setSound(Optional<KoreanBuildSound> newSound) {
        this.sound = newSound;
    }

    public KoreanBuildStats getStats() {
        return this.stats.orElse(null);
    }

    public void setStats(Optional<KoreanBuildStats> newStats) {
        this.stats = newStats;
    }

    public String getUnicode() {
        return this.unicode.orElse("None");
    }

    public void setUnicode(Optional<String> newUnicode) {
        this.unicode = newUnicode;
    }

    public ArrayList<CustomID<KoreanBuild>> getConsonants() {
        return this.consonants.orElse(new ArrayList<CustomID<KoreanBuild>>());
    }

    public void setConsonants(Optional<ArrayList<CustomID<KoreanBuild>>> newConsonants) {
        this.consonants = newConsonants;
    }

    public ArrayList<CustomID<KoreanBuild>> getVowels() {
        return this.vowels.orElse(new ArrayList<CustomID<KoreanBuild>>());
    }

    public void setVowels(Optional<ArrayList<CustomID<KoreanBuild>>> newVowels) {
        this.vowels = newVowels;
    }

    public ArrayList<CustomID<KorEngDictionary>> getDictionary() {
        return this.dictionary.orElse(new ArrayList<CustomID<KorEngDictionary>>());
    }

    @Override
    public String toString() {
        String ret = String.format(
            """
            [
                Official ID=%s
                Korean Build ID=%s
                Speech=%s
                Build=%s
                Complete=%b
                Unicode=%s
                Sound=%s
                Stats=%s
                Consonants=%s
                Vowels=%s
                Dictionary=%s
            ]       
            """,
            id, kbid, 
            speech, 
            build, 
            complete,
            unicode,
            sound,
            stats,
            consonants,
            vowels,
            dictionary
        );
        return ret.indent(2);
    }
}

class KoreanBuildSound {

    private String key;
    private boolean silent;
    private boolean replace;
    private Optional<KoreanPronunciation> pronunciation;

    public KoreanBuildSound() {}

    public KoreanBuildSound(
        String key,
        boolean silent,
        boolean replace
    ) {
        this.key = key;
        this.silent = silent;
        this.replace = replace;
    }

    public KoreanBuildSound(
        String key,
        boolean silent,
        boolean replace,
        KoreanPronunciation pronunciation
    ) {
        this.key = key;
        this.silent = silent;
        this.replace = replace;
        this.pronunciation = Optional.ofNullable(pronunciation);
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
        String ret = String.format(
            """
            [
                Key=%s
                Silent=%b
                Replace=%b
                Pronunciation=%s
            ]        
            """,
            key, silent, replace, pronunciation
        );
        return ret.indent(2);
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
        String ret = String.format(
            """
            [
                Frequency=%d
                Appearences=%d
            ]  
            """,
            frequency, appearences
        );
        return ret.indent(2);
    }
}