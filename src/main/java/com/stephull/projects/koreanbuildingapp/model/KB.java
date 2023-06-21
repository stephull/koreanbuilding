package com.stephull.projects.koreanbuildingapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "kbb_builds")
@Data
public class KB {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "character")
    private String character;

    @Column(name = "build")
    private String build;

    @Column(name = "complete")
    private boolean complete;

    @Column(name = "sound_key")
    private String soundKey;

    @Column(name = "sound_silent")
    private boolean soundSilent;

    @Column(name = "sound_replace")
    private boolean soundReplace;

    @Column(name = "sound_pronunciation_type", nullable = true)
    private String soundPronunciationType;

    @Column(name = "sound_pronunciation_additional_character", nullable = true)
    private String soundPronunciationAdditionalCharacter;

    @Column(name = "sound_pronunciation_additional_sound", nullable = true)
    private String soundPronunciationAdditionalSound;

    @Column(name = "sound_pronunciation_replacement_character", nullable = true)
    private String soundPronunciationReplacementCharacter;

    @Column(name = "sound_pronunciation_replacement_sound", nullable = true)
    private String soundPronunciationReplacementSound;

    @Column(name = "sound_pronunciation_combination_character", nullable = true)
    private String soundPronunciationCombinationCharacter;

    @Column(name = "sound_pronunciation_combination_sound", nullable = true)
    private String soundPronunciationCombinationSound;

    @Column(name = "stats_frequency")
    private double statsFrequency;

    @Column(name = "stats_appearences")
    private double statsAppearences;

    @Column(name = "unicode")
    private String unicode;

    // constructors, and get-set methods
    public KB() {}

    public KB(
        String character,
        String build,
        boolean complete
    ) {
        this.character = character;
        this.build = build;
        this.complete = complete;
    }

    // advanced constructor (without pronunciation)
    public KB(
        String character,
        String build,
        boolean complete,
        String soundKey,
        boolean soundSilent,
        boolean soundReplace,
        double statsFrequency,
        double statsAppearences,
        String unicode
    ) {
        this.character = character;
        this.build = build;
        this.complete = complete;
        this.soundKey = soundKey;
        this.soundSilent = soundSilent;
        this.soundReplace = soundReplace;
        this.statsFrequency = statsFrequency;
        this.statsAppearences = statsAppearences;
        this.unicode = unicode;
    }

    // advanced constructor (all components including pronunciation)
    public KB(
        String character,
        String build,
        boolean complete,
        String soundKey,
        boolean soundSilent,
        boolean soundReplace,
        double statsFrequency,
        double statsAppearences,
        String unicode,
        String soundPronunciationType,
        String soundPronunciationAdditionalCharacter,
        String soundPronunciationAdditionalSound,
        String soundPronunciationReplacementCharacter,
        String soundPronunciationReplacementSound,
        String soundPronunciationCombinationCharacter,
        String soundPronunciationCombinationSound
    ) {
        this.character = character;
        this.build = build;
        this.complete = complete;
        this.soundKey = soundKey;
        this.soundSilent = soundSilent;
        this.soundReplace = soundReplace;
        this.statsFrequency = statsFrequency;
        this.statsAppearences = statsAppearences;
        this.unicode = unicode;
        this.soundPronunciationType = soundPronunciationType;
        this.soundPronunciationAdditionalCharacter = soundPronunciationAdditionalCharacter;
        this.soundPronunciationAdditionalSound = soundPronunciationAdditionalSound;
        this.soundPronunciationReplacementCharacter = soundPronunciationReplacementCharacter;
        this.soundPronunciationReplacementSound = soundPronunciationReplacementSound;
        this.soundPronunciationCombinationCharacter = soundPronunciationCombinationCharacter;
        this.soundPronunciationCombinationSound = soundPronunciationCombinationSound;
    }

    public long getId() {
        return this.id;
    }

    public String getCharacter() {
        return this.character;
    }

    public void setCharacter(String newCharacter) {
        this.character = newCharacter;
    }

    public String getBuild() {
        return this.build;
    }

    public void setBuild(String newBuild) {
        this.build = newBuild;
    }

    public boolean getCompleteStatus() {
        return this.complete;
    }

    public void setCompleteStatus(boolean newStatus) {
        this.complete = newStatus;
    }

    public String getSoundKey() {
        return this.soundKey;
    }

    public void setSoundKey(String newSoundKey) {
        this.soundKey = newSoundKey;
    }

    public boolean getSoundSilent() {
        return this.soundSilent;
    }

    public void setSoundSilent(boolean newSoundSilent) {
        this.soundSilent = newSoundSilent;
    }

    public boolean getSoundReplace() {
        return this.soundReplace;
    }

    public void setSoundReplace(boolean newSoundReplace) {
        this.soundReplace = newSoundReplace;
    }

    public String getSoundPronunciationType() {
        return this.soundPronunciationType;
    }

    public void setSoundPronunciationType(String newSoundPronunciationType) {
        this.soundPronunciationType = newSoundPronunciationType;
    }

    public String getSoundPronunciationAdditionalCharacter() {
        return this.soundPronunciationAdditionalCharacter;
    }

    public void setSoundPronunciationAdditionalCharacter(String newCharacter) {
        this.soundPronunciationAdditionalCharacter = newCharacter;
    }

    public String getSoundPronunciationAdditionalSound() {
        return this.soundPronunciationAdditionalSound;
    }

    public void setSoundPronunciationAdditionalSound(String newSound) {
        this.soundPronunciationAdditionalSound = newSound;
    }

    public String getSoundPronunciationReplacementCharacter() {
        return this.soundPronunciationReplacementCharacter;
    }

    public void setSoundPronunciationReplacementCharacter(String newCharacter) {
        this.soundPronunciationReplacementCharacter = newCharacter;
    }

    public String getSoundPronunciationReplacementSound() {
        return this.soundPronunciationReplacementSound;
    }

    public void setSoundPronunciationReplacementSound(String newSound) {
        this.soundPronunciationReplacementSound = newSound;
    }

    public String getSoundPronunciationCombinationCharacter() {
        return this.soundPronunciationCombinationCharacter;
    }

    public void setSoundPronunciationCombinationCharacter(String newCharacter) {
        this.soundPronunciationCombinationCharacter = newCharacter;
    }

    public String getSoundPronunciationCombinationSound() {
        return this.soundPronunciationCombinationSound;
    }

    public void setSoundPronunciationCombinationSound(String newSound) {
        this.soundPronunciationCombinationSound = newSound;
    }

    public double getStatsFrequency() {
        return this.statsFrequency;
    }

    public void setStatsFrequency(double newFrequency) {
        this.statsFrequency = newFrequency;
    }

    public double getStatsAppearences() {
        return this.statsAppearences;
    }

    public void setStatsAppearences(double newAppearences) {
        this.statsAppearences = newAppearences;
    }

    public String getUnicode() {
        return this.unicode;
    }

    public void setUnicode(String newUnicode) {
        this.unicode = newUnicode;
    }

    @Override
    public String toString() {
        return String.format(
            "Korean Character Build: [id=%d, character=%s, build=%s, complete=%b]",
            id, character, build, complete
        );
    }
}
