package com.stephull.projects.koreanbuildingapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Document(collection="KoreanBuilds")
public class KoreanBuild {

    @Id
    private String id;

    private CustomID<KoreanBuild> kbid;
    private KoreanSpeechCluster base;
    private String build;
    private boolean complete;
    private String unicode;

    private Optional<KoreanBuildSound> sound;
    private Optional<KoreanBuildStats> stats;

    private Optional<List<CustomID<KoreanBuild>>> consonants;
    private Optional<List<CustomID<KoreanBuild>>> vowels;

    public KoreanBuild() {}
    
    // for demo purposes
    public KoreanBuild(
        KoreanSpeechCluster base,
        String build,
        boolean complete,
        String unicode
    ) {
        this.base = base;
        this.build = build;
        this.complete = complete;
        this.unicode = unicode;
    }

    // normal constructor
    public KoreanBuild(
        KoreanSpeechCluster base,
        String build,
        boolean complete,
        String unicode,
        KoreanBuildSound sound,
        KoreanBuildStats stats
    ) {
        this.base = base;
        this.build = build;
        this.complete = complete;
        this.unicode = unicode;
        this.sound = Optional.ofNullable(sound);
        this.stats = Optional.ofNullable(stats);
    }

    // with true-optional parameters
    public KoreanBuild(
        KoreanSpeechCluster base,
        String build,
        boolean complete,
        KoreanBuildSound sound,
        KoreanBuildStats stats,
        String unicode,
        List<CustomID<KoreanBuild>> consonants,
        List<CustomID<KoreanBuild>> vowels,
        List<CustomID<KorEngDictionary>> dictionary
    ) {
        this.base = base;
        this.build = build;
        this.complete = complete;
        this.unicode = unicode;
        this.sound = Optional.ofNullable(sound);
        this.stats = Optional.ofNullable(stats);
        this.consonants = Optional.ofNullable(consonants);
        this.vowels = Optional.ofNullable(vowels);
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

    public KoreanSpeechCluster getBase() {
        return this.base;
    }

    public void setBase(KoreanSpeechCluster newBase) {
        this.base = newBase;
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

    public String getUnicode() {
        return this.unicode;
    }

    public void setUnicode(String newUnicode) {
        this.unicode = newUnicode;
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

    public List<CustomID<KoreanBuild>> getConsonants() {
        return this.consonants.orElse(new ArrayList<CustomID<KoreanBuild>>());
    }

    public void setConsonants(Optional<List<CustomID<KoreanBuild>>> newConsonants) {
        this.consonants = newConsonants;
    }

    public List<CustomID<KoreanBuild>> getVowels() {
        return this.vowels.orElse(new ArrayList<CustomID<KoreanBuild>>());
    }

    public void setVowels(Optional<List<CustomID<KoreanBuild>>> newVowels) {
        this.vowels = newVowels;
    }

    @Override
    public String toString() {
        String ret = String.format(
            """
            [
                Official ID=%s
                Korean Build ID=%s
                Assembly=%s
                Build=%s
                Complete=%b
                Unicode=%s
                Sound=%s
                Stats=%s
                Consonants=%s
                Vowels=%s
            ]       
            """,
            id, kbid, 
            base,
            build, 
            complete,
            unicode,
            sound,
            stats,
            consonants,
            vowels
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