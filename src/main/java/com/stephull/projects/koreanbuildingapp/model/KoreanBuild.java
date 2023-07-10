package com.stephull.projects.koreanbuildingapp.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.stephull.projects.koreanbuildingapp.repository.KBRepository;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Document(collection = "KoreanBuilds")
public class KoreanBuild {

    @Autowired
    private KBRepository kbrepo;

    @Id
    private String id;

    private CustomID<KoreanBuild> kbid;
    private KoreanSpeechCluster base;
    private String build;

    private String unicode;
    private KoreanBuildSound sound;
    private KoreanBuildStats stats;
    //private LinkedList<KoreanSpeechCluster> assembly;

    private List<KoreanBuild> consonants;
    private List<KoreanBuild> vowels;

    public KoreanBuild() {}

    // normal constructor
    public KoreanBuild(
        String build,
        KoreanSpeechCluster base,
        String unicode
    ) {
        this.base = base;
        this.build = build;
        this.unicode = unicode;
        this.sound = new KoreanBuildSound(build);
        this.stats = new KoreanBuildStats(build);
        //this.assembly = new LinkedList<KoreanSpeechCluster>(build);
        this.consonants = kbrepo.findAllConsonantsByBuild(build);
        this.vowels = kbrepo.findAllVowelsByBuild(build);
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

    public String getUnicode() {
        return this.unicode;
    }

    public void setUnicode(String newUnicode) {
        this.unicode = newUnicode;
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

    /*public LinkedList<KoreanSpeechCluster> getAssembly() {
        return this.assembly;
    }

    public void setAssembly(LinkedList<KoreanSpeechCluster> newAssembly) {
        this.assembly = newAssembly;
    }*/

    public List<KoreanBuild> getConsonants() {
        return this.consonants;
    }

    public void setConsonants(List<KoreanBuild> newConsonants) {
        this.consonants = newConsonants;
    }

    public List<CustomID<KoreanBuild>> getVowels() {
        return this.vowels;
    }

    public void setVowels(List<CustomID<KoreanBuild>> newVowels) {
        this.vowels = newVowels;
    }

    @Override
    public String toString() {
        String ret = String.format(
            """
            [
                Official ID=%s
                Korean Build ID=%s
                Build=%s
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
            unicode,
            sound,
            stats,
            //assembly,
            consonants,
            vowels
        );
        return ret.indent(2);
    }

    private class KoreanBuildSound {

        private String key;
        private boolean silent;
        private boolean replace;
        private Optional<KoreanPronunciation> pronunciation;

        public KoreanBuildSound(String build) {
            key = kbrepo.findSoundKeyByBuild(build);
            
            boolean[] special = kbrepo.findSpecialSingularPronunciationByBuild(build);
            silent = special[0];
            replace = special[1];

            pronunciation = kbrepo.findPronunciationByBuild(build);
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

    private class KoreanBuildStats {

        private double frequency;
        private int appearences;

        public KoreanBuildStats(String build) {
            frequency = kbrepo.findAppearencesStatsByBuild(build);
            appearences = kbrepo.findFrequencyStatsByBuild(build);
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
}

class KoreanBuildDemo {
    public static void main(String[] args) {
        //KoreanBuild kb1 = new KoreanBuild(base1, "", "", kbsSound, kbsStats, assembly1);
    }
}