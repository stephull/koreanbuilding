package com.stephull.projects.koreanbuildingapp.model;

import java.util.Optional;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="KoreanSpeech")
public class KoreanSpeech {

    @Id
    private String id;

    private CustomID<KoreanSpeech> ksid;
    private Jamo<?> letter;
    private String normalSound;
    private Optional<String> endingSound;

    public KoreanSpeech() {}

    public KoreanSpeech(
        Jamo<?> letter,
        String normalSound
    ) {
        this.letter = letter;
        this.normalSound = normalSound;
    }

    public KoreanSpeech(
        Jamo<?> letter,
        String normalSound,
        String endingSound
    ) {
        this.letter = letter;
        this.normalSound = normalSound;
        this.endingSound = Optional.ofNullable(endingSound);
    }

    public String getId() {
        return this.id;
    }

    public String getKsId() {
        return this.ksid.getCustomID();
    }

    public void setKsId(String newKsId) {
        this.ksid.setCustomID(newKsId);
    }

    public String getLetter() {
        return this.letter.getValue();
    }

    public void setLetter(String newLetter) {
        this.letter.setValue(newLetter);
    }

    public String getNormalSound() {
        return this.normalSound;
    }

    public void setNormalSound(String newNormalSound) {
        this.normalSound = newNormalSound;
    }

    public String getEndingSound() {
        return this.endingSound.orElse("None");
    }

    public void setEndingSound(Optional<String> newEndingSound) {
        this.endingSound = newEndingSound;
    }

    @Override
    public String toString() {
        String ret = String.format(
            """
            [
                Letter=%s
                Normal sound=%s
                Ending sound=%s
            ]
            """,
            letter, normalSound, endingSound
        );
        return ret.indent(2);
    }

}