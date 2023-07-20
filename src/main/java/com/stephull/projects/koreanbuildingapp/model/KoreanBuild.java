package com.stephull.projects.koreanbuildingapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Optional;

@Document(collection = "KoreanBuilds")
public class KoreanBuild {

    @Id
    private String id;

    private CustomID<KoreanBuild> kbid;
    private String build;
    private String unicode;

    private KoreanBuildSound sound;
    private KoreanBuildStats stats;
    private List<KoreanSpeechCluster> assembly;
    private KoreanSpeechCluster base;

    private Optional<List<KoreanBuild>> consonants;
    private Optional<List<KoreanBuild>> vowels;

    public KoreanBuild() {}

    // normal constructor
    public KoreanBuild(
        String build,
        String unicode,
        KoreanBuildSound sound,
        KoreanBuildStats stats,
        List<KoreanSpeechCluster> assembly,
        KoreanSpeechCluster base,
        Optional<List<KoreanBuild>> consonants,
        Optional<List<KoreanBuild>> vowels
    ) {
        this.build = build;
        this.unicode = unicode;
        this.sound = sound;
        this.stats = stats;
        this.assembly = assembly;
        this.base = base;
        this.consonants = consonants;
        this.vowels = vowels;
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

    public List<KoreanSpeechCluster> getAssembly() {
        return this.assembly;
    }

    public void setAssembly(List<KoreanSpeechCluster> newAssembly) {
        this.assembly = newAssembly;
    }

    public List<KoreanBuild> getConsonants() {
        return this.consonants.orElse(null);
    }

    public void setConsonants(Optional<List<KoreanBuild>> newConsonants) {
        this.consonants = newConsonants;
    }

    public List<KoreanBuild> getVowels() {
        return this.vowels.orElse(null);
    }

    public void setVowels(Optional<List<KoreanBuild>> newVowels) {
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
                Assembly=%s
                Base=%s
            ]
            """,
            id, kbid,
            build,
            unicode,
            sound,
            stats,
            assembly,
            base,
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
}

class KoreanBuildDemo {
    public static void main(String[] args) {
        //KoreanBuild kb1 = new KoreanBuild();
    }
}