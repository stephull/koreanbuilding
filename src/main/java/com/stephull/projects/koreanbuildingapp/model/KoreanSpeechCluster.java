package com.stephull.projects.koreanbuildingapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="KoreanSpeechCluster")
public class KoreanSpeechCluster {

    @Id
    private String id;

    //private CustomID<KoreanSpeechCluster> kscid;
    private String letter;
    private String romanization;
    private String type;

    public KoreanSpeechCluster() {}

    public KoreanSpeechCluster(
        String letter,
        String romanization,
        String type
    ) {
        this.letter = letter;
        this.romanization = romanization;
        this.type = type;
    }

    public String getId() {
        return this.id;
    }

    /*public String getKscId() {
        return this.kscid.getCustomID();
    }

    public void setKscId(String newKscId) {
        this.kscid.setCustomID(newKscId);
    }*/

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

    public String getType() {
        return this.type;
    }

    public void setType(String newType) {
        this.type = newType;
    }

    @Override
    public String toString() {
        String ret = String.format(
            """
            [
                Letter=%s
                Romanized sound=%s
                Speech type=%s
            ]
            """,
            letter, romanization, type
        );
        return ret.indent(2);
    }

}