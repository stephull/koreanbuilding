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
    private KoreanBuildSound sound;
    private KoreanBuildStats stats;
    private String unicode;
    private Optional<ArrayList<CustomID<KoreanBuild>>> consonants;
    private Optional<ArrayList<CustomID<KoreanBuild>>> vowels;
    private Optional<ArrayList<CustomID<KorEngDictionary>>> dictionary;

    public KoreanBuild() {}
    
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
        this.sound = sound;
        this.stats = stats;
        this.unicode = unicode;
    }

    // with optional parameters
    public KoreanBuild(
        KoreanSpeech speech,
        String build,
        boolean complete,
        KoreanBuildSound sound,
        KoreanBuildStats stats,
        String unicode,
        Optional<ArrayList<CustomID<KoreanBuild>>> consonants,
        Optional<ArrayList<CustomID<KoreanBuild>>> vowels,
        Optional<ArrayList<CustomID<KorEngDictionary>>> dictionary
    ) {
        this.speech = speech;
        this.build = build;
        this.complete = complete;
        this.sound = sound;
        this.stats = stats;
        this.unicode = unicode;
        this.consonants = consonants;
        this.vowels = vowels;
        this.dictionary = dictionary;
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
        return this.sound;
    }

    public void setSound(KoreanBuildSound newSound) {
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
        return String.format(
            """
            [
                Official ID=%s
                Korean Build ID=%s
                Speech=%s
                Build=%s
                Unicode=%s
                Complete=%b
                Sound=%s
                Stats=%s
                Consonants=%s
                Vowels=%s
                Dictionary=%s
            ]       
            """,
            id, kbid, speech, build, unicode, complete, 
            sound.toString(), stats.toString(), 
            consonants.orElse(new ArrayList<CustomID<KoreanBuild>>()).toString(),
            vowels.orElse(new ArrayList<CustomID<KoreanBuild>>()).toString(),
            dictionary.orElse(new ArrayList<CustomID<KorEngDictionary>>()).toString()
        );
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