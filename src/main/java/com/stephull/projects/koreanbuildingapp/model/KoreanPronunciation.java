package com.stephull.projects.koreanbuildingapp.model;

import java.util.Optional;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="KoreanPronunciation")
public class KoreanPronunciation {

    @Id
    private String id;

    private CustomID<KoreanPronunciation> kpid;
    private PronunciationType type;
    private Optional<KPProperty> additional;
    private Optional<KPProperty> replacement;
    private Optional<KPProperty> combination;

    public KoreanPronunciation() {}

    public KoreanPronunciation(
        PronunciationType type
    ) {
        this.type = type;
    }

    public KoreanPronunciation(
        PronunciationType type,
        KPProperty additional
    ) {
        this.type = type;
        this.additional = Optional.ofNullable(additional);
    }

    public KoreanPronunciation(
        PronunciationType type,
        KPProperty additional,
        KPProperty replacement,
        KPProperty combination
    ) {
        this.type = type;
        this.additional = Optional.ofNullable(additional);
        this.replacement = Optional.ofNullable(replacement);
        this.combination = Optional.ofNullable(combination);
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

    public KPProperty getAdditional() {
        return this.additional.orElse(null);
    }

    public void setAdditional(Optional<KPProperty> newAdditional) {
        this.additional = newAdditional;
    }

    public KPProperty getReplacement() {
        return this.replacement.orElse(null);
    }

    public void setReplacement(Optional<KPProperty> newReplacement) {
        this.replacement = newReplacement;
    }

    public KPProperty getCombination() {
        return this.combination.orElse(null);
    }

    public void setCombination(Optional<KPProperty> newCombination) {
        this.combination = newCombination;
    }

    @Override
    public String toString() {
        String ret = String.format(
            """
            [
                Pronunciation type=%s
                Additional=%s
                Replacement=%s
                Combination=%s
            ]      
            """,
            type, additional, replacement, combination
        );
        return ret.indent(2);
    }
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