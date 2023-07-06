package com.stephull.projects.koreanbuildingapp.model;

import java.util.Optional;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="KoreanSpeech")
public class KoreanSpeech {

    @Id
    private String id;

    private CustomID<KoreanSpeech> ksid;
    private String letter;
    private KoreanSpeechSound sound;

    public KoreanSpeech () {}

    public KoreanSpeech (
        String letter,
        KoreanSpeechSound sound
    ) {
        this.letter = letter;
        this.sound = sound;
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
        return this.letter;
    }

    public void setLetter(String newLetter) {
        this.letter = newLetter;
    }

    public KoreanSpeechSound getSound() {
        return this.sound;
    }

    public void setSound(KoreanSpeechSound newSound) {
        this.sound = newSound;
    }

    @Override
    public String toString() {
        return String.format(
            """
            [
                Letter=%s
                Sound=%s
            ]
            """,
            letter, sound.toString()
        );
    }

}

class KoreanSpeechSound {

    private String normal;
    private Optional<String> ending;

    public KoreanSpeechSound() {}

    public KoreanSpeechSound(
        String normal
    ) {
        this.normal = normal;
    }

    public KoreanSpeechSound(
        String normal,
        Optional<String> ending
    ) {
        this.normal = normal;
        this.ending = ending;
    }

    public String getNormalSound() {
        return this.normal;
    }

    public void setNormalSound(String newNormal) {
        this.normal = newNormal;
    }

    public String getEndingSound() {
        return this.ending.orElse("");
    }

    public void setEndingSound(Optional<String> newEnding) {
        this.ending = newEnding;
    }

    @Override
    public String toString() {
        return String.format(
            """
            [
                Normal sound=%s
                Ending sound=%s
            ]
            """,
            normal, ending.orElse("None")
        );
    }

}
