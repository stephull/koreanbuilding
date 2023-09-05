package com.stephull.projects.koreanbuildingapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "KoreanBuilds")
public class KoreanBuild {

    @Id
    @Field("_id") 
    private String id;

    @Field("kbid")
    private CustomID<KoreanBuild> kbid;
    
    private String build;
    private String unicode;

    private int phase;

    private KoreanBuildSound sound;
    private KoreanBuildStats stats;

    private KoreanBuild root;
    private KoreanBuild parent;

    private List<KoreanSpeechCluster> assembly;
    private KoreanSpeechCluster base;

    private List<KoreanBuild> children;

    public KoreanBuild() {
        this.kbid = new CustomID<KoreanBuild>("0", KoreanBuild.class.getName());
    }

    public KoreanBuild(
        String build,
        String unicode,
        int phase,
        KoreanBuildSound sound,
        KoreanBuildStats stats,
        KoreanBuild root,
        KoreanBuild parent,
        List<KoreanSpeechCluster> assembly,
        KoreanSpeechCluster base,
        List<KoreanBuild> children
    ) {
        this.build = build;
        this.unicode = unicode;
        this.phase = phase;
        this.sound = sound;
        this.stats = stats;
        this.root = root;
        this.parent = parent;
        this.assembly = assembly;
        this.base = base;
        this.children = children;

        this.kbid = new CustomID<KoreanBuild>("0", KoreanBuild.class.getName());
    }

    public String getId() {
        return this.id;
    }

    public CustomID<KoreanBuild> getKbid() {
        return this.kbid;
    }

    public void setKbId(CustomID<KoreanBuild> newKbId) {
        this.kbid = newKbId;
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

    public int getPhase() {
        return this.phase;
    }

    public void setPhase(int newPhase) {
        this.phase = newPhase;
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

    public KoreanBuild getRoot() {
        return this.root;
    }

    public void setRoot(KoreanBuild newRoot) {
        this.root = newRoot;
    }

    public KoreanBuild getParent() {
        return this.parent;
    }

    public void setParent(KoreanBuild newParent) {
        this.parent = newParent;
    }

    public List<KoreanSpeechCluster> getAssembly() {
        return this.assembly;
    }

    public void setAssembly(List<KoreanSpeechCluster> newAssembly) {
        this.assembly = newAssembly;
    }

    public List<KoreanBuild> getChildren() {
        return this.children;
    }

    public void setConsonants(List<KoreanBuild> newChildren) {
        this.children = newChildren;
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
                Experience phase=%d
                Sound=%s
                Stats=%s
                Root=%s
                Parent=%s
                Assembly=%s
                Base=%s
                Children=%s
            ]
            """,
            id, kbid,
            build,
            unicode,
            phase,
            sound,
            stats,
            root,
            parent,
            assembly,
            base,
            children
        );
        return ret.indent(2);
    }

    private class KoreanBuildSound {

        private String key;
        private boolean silent;
        private boolean replace;
        private KoreanPronunciation pronunciation;

        public KoreanBuildSound(
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