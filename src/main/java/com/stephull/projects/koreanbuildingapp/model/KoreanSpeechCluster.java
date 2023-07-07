package com.stephull.projects.koreanbuildingapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="KoreanSpeechCluster")
public class KoreanSpeechCluster {

    @Id
    private String id;

    private CustomID<KoreanSpeechCluster> kscid;
    private String letter;
    private String romanization;
    private SpeechType type;

    public KoreanSpeechCluster() {}

    public KoreanSpeechCluster(
        String letter,
        String romanization,
        SpeechType type
    ) {
        this.letter = letter;
        this.romanization = romanization;
        this.type = type;
    }

    public String getId() {
        return this.id;
    }

    public String getKscId() {
        return this.kscid.getCustomID();
    }

    public void setKscId(String newKscId) {
        this.kscid.setCustomID(newKscId);
    }

    public String getLetter() {
        return this.letter;
    }

    public void setLetter(String newLetter) {
        this.letter = newLetter;
    }

    public String getRomanization() {
        return this.romanization;
    }

    public void setRomanization(String newRomanization) {
        this.romanization = newRomanization;
    }

    public SpeechType getType() {
        return this.type;
    }

    public void setType(SpeechType newType) {
        this.type = newType;
    }

    @Override
    public String toString() {
        String ret = String.format(
            """
            [
                Letter=%s
                Romanized sound=%s
            ]
            """,
            letter, romanization
        );
        return ret.indent(2);
    }

}