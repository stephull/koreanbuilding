package com.stephull.projects.koreanbuildingapp.model;

import java.util.Optional;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="KoreanPronunciation")
public class KoreanPronunciation {

    @Id
    private String id;

    private CustomID<KoreanPronunciation> kpid;
    private String type;
    private KoreanPronunciationProperty additional;
    private Optional<KoreanPronunciationProperty> replacement;
    private Optional<KoreanPronunciationProperty> combination;

    public KoreanPronunciation() {}

    public KoreanPronunciation(
        String type,
        KoreanPronunciationProperty additional
    ) {
        this.type = type;
        this.additional = additional;
    }

    public KoreanPronunciation(
        String type,
        KoreanPronunciationProperty additional,
        KoreanPronunciationProperty replacement,
        KoreanPronunciationProperty combination
    ) {
        this.type = type;
        this.additional = additional;
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

    public String getType() {
        return this.type;
    }

    public void setType(String newType) {
        this.type = newType;
    }

    public KoreanPronunciationProperty getAdditional() {
        return this.additional;
    }

    public void setAdditional(KoreanPronunciationProperty newAdditional) {
        this.additional = newAdditional;
    }

    public KoreanPronunciationProperty getReplacement() {
        return this.replacement.orElse(null);
    }

    public void setReplacement(Optional<KoreanPronunciationProperty> newReplacement) {
        this.replacement = newReplacement;
    }

    public KoreanPronunciationProperty getCombination() {
        return this.combination.orElse(null);
    }

    public void setCombination(Optional<KoreanPronunciationProperty> newCombination) {
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

class KoreanPronunciationProperty {

    private String character;
    private String sound;

    public KoreanPronunciationProperty() {}

    public KoreanPronunciationProperty(
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