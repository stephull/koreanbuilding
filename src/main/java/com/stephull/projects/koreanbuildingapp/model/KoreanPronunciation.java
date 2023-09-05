package com.stephull.projects.koreanbuildingapp.model;

import java.util.Optional;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="KoreanPronunciation")
public class KoreanPronunciation {

    @Id 
    @Field("_id")
    private String id;

    @Field("kpid")
    private CustomID<KoreanPronunciation> kpid;
    
    private PronunciationType type;
    private Optional<KPProperty> previous;
    private Optional<KPProperty> replaceBase;
    private Optional<KPProperty> replacePrevious;

    public KoreanPronunciation() {}

    public KoreanPronunciation(
        PronunciationType type
    ) {
        this.type = type;
    }

    public KoreanPronunciation(
        PronunciationType type,
        KPProperty previous
    ) {
        this.type = type;
        this.previous = Optional.ofNullable(previous);
    }

    public KoreanPronunciation(
        PronunciationType type,
        KPProperty previous,
        KPProperty replaceBase,
        KPProperty replacePrevious
    ) {
        this.type = type;
        this.previous = Optional.ofNullable(previous);
        this.replaceBase = Optional.ofNullable(replaceBase);
        this.replacePrevious = Optional.ofNullable(replacePrevious);
    }

    public String getId() {
        return this.id;
    }

    public String getKpId() {
        return this.kpid.getCustomID();
    }

    public void setKpId(String newKpId) {
        this.kpid.setCustomID(newKpId);
    }

    public PronunciationType getType() {
        return this.type;
    }

    public void setType(PronunciationType newType) {
        this.type = newType;
    }

    public KPProperty getPrevious() {
        return this.previous.orElse(null);
    }

    public void setPrevious(Optional<KPProperty> newPrevious) {
        this.previous = newPrevious;
    }

    public KPProperty getReplaceBase() {
        return this.replaceBase.orElse(null);
    }

    public void setReplaceBase(Optional<KPProperty> newReplaceBase) {
        this.replaceBase = newReplaceBase;
    }

    public KPProperty getReplacePrevious() {
        return this.replacePrevious.orElse(null);
    }

    public void setReplacePrevious(Optional<KPProperty> newReplacePrevious) {
        this.replacePrevious = newReplacePrevious;
    }

    @Override
    public String toString() {
        String ret = String.format(
            """
            [
                Pronunciation type=%s
                Base=%s
                Previous=%s
                Replace base=%s
                Replace previous=%s
            ]      
            """,
            type, "", previous, replaceBase, replacePrevious
        );
        return ret.indent(2);
    }

    // example: 입력
    // base: ㄹ
    // previous: ㅂ
    // replaceBase: ㄴ
    // replacePrevious: ㅁ  
}

class KPProperty {

    private String character;
    private String sound;

    public KPProperty() {}

    public KPProperty(
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
        String ret = String.format(
            """
            [
                Pronunciation character=%s
                Pronunciation sound=%s
            ]   
            """,
            character, sound
        );
        return ret.indent(2);
    }
}