package com.stephull.projects.koreanbuildingapp.model;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="KoreanSpeechCluster")
public class KoreanSpeechCluster implements RelationshipManager<KoreanSpeechCluster>{

    @Id 
    @Field("_id")
    private String id;

    @Field("kscid")
    private CustomID<KoreanSpeechCluster> kscid;
    
    private String letter;
    private String positioned;
    private String romanized;
    private String type;
    
    private Map<KoreanSpeechCluster, KoreanSpeechCluster> compounds;

    public KoreanSpeechCluster() {
        this.kscid = new CustomID<>("0", KoreanSpeechCluster.class.getName());
    }

    public KoreanSpeechCluster(
        String letter,
        String positioned,
        String romanized,
        String type
    ) {
        this.letter = letter;
        this.positioned = positioned;
        this.romanized = romanized;
        this.type = type;

        this.compounds = new LinkedHashMap<KoreanSpeechCluster, KoreanSpeechCluster>();
        this.kscid = new CustomID<>("0", KoreanSpeechCluster.class.getName());
    }

    public String getId() {
        return this.id;
    }

    public CustomID<KoreanSpeechCluster> getKscId() {
        return this.kscid;
    }

    public void setKscId(CustomID<KoreanSpeechCluster> newKscId) {
        this.kscid = newKscId;
    }

    public String getLetter() {
        return this.letter;
    }

    public void setLetter(String newLetter) {
        this.letter = newLetter;
    }

    public String getPositioned() {
        return this.positioned;
    }

    public void setPositioned(String newPositioned) {
        this.positioned = newPositioned;
    }

    public String getRomanized() {
        return this.romanized;
    }

    public void setRomanized(String newRomanized) {
        this.romanized = newRomanized;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String newType) {
        this.type = newType;
    }

    @Override
    public void addRelationship(KoreanSpeechCluster k, KoreanSpeechCluster v) {
        compounds.put(k, v);
    }

    @Override
    public Map<KoreanSpeechCluster, KoreanSpeechCluster> getRelationships() {
        return compounds;
    }

    @Override
    public String toString() {
        String ret = String.format(
            """
            [
                Letter=%s
                Positioned Unicode equivalent=%s
                Romanized sound=%s
                Speech type=%s
                Compound pairs=%s
            ]
            """,
            letter, positioned, romanized, type, compounds
        );
        return ret.indent(2);
    }

}