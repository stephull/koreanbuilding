package com.stephull.projects.koreanbuildingapp.model;

import java.lang.Override;

import java.util.ArrayList;
import java.util.Optional;
import java.util.LinkedHashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="KorEngDictionary")
public class KorEngDictionary {
    
    @Id
    private String id;

    private String entry;
    private ArrayList<DictionaryDefinition> definitions;
    private AuditoryData sound;
    private Optional<VisualData> image;

    public KorEngDictionary() {}

    public KorEngDictionary(
        String entry,
        ArrayList<DictionaryDefinition> definitions,
        AuditoryData sound
    ) {
        this.entry = entry;
        this.definitions = definitions;
        this.sound = sound;
    }

    public KorEngDictionary(
        String entry,
        ArrayList<DictionaryDefinition> definitions,
        AuditoryData sound,
        Optional<VisualData> image
    ) {
        this.entry = entry;
        this.definitions = definitions;
        this.sound = sound;
        this.image = image;
    }

    public String getId() {
        return this.id;
    }

    public String getEntry() {
        return this.entry;
    }

    public void setEntry(String newEntry) {
        this.entry = newEntry;
    }

    public ArrayList<DictionaryDefinition> getDefinitions() {
        return this.definitions;
    }

    public void setDefinitions(ArrayList<DictionaryDefinition> newDefinitions) {
        this.definitions = newDefinitions;
    }

    public AuditoryData getSound() {
        return this.sound;
    }

    public void setSound(AuditoryData newSound) {
        this.sound = newSound;
    }

    public VisualData getImage() {
        return this.image.orElse(null);
    }

    public void setImage(Optional<VisualData> newImage) {
        this.image = newImage;
    }

    @Override
    public String toString() {
        return String.format(
            """
            [
                ID=%s
                Entry=%s
                Definitions=%s
                Sound data=%s
                Image data=%s
            ]        
            """,
            id, entry, definitions.toString(), sound.toString(),
            image.orElse(null).toString()
        );
    }

}

class DictionaryDefinition {

    private String translation;
    private String entryType;    // noun, verb, adverb, etc.
    private String meaning;
    private Optional<String> context;   // a.k.a examples
    private Optional<LinkedHashMap<ChineseCharacter, KoreanBuild>> hanja;

    public String getTranslation() {
        return this.translation;
    }

    public void setTranslation(String newTranslation) {
        this.translation = newTranslation;
    }

    public String getEntryType() {
        return this.entryType;
    }

    public void setEntryType(String newEntryType) {
        this.entryType = newEntryType;
    }

    public String getMeaning() {
        return this.meaning;
    }

    public void setMeaning(String newMeaning) {
        this.meaning = newMeaning;
    }

    public String getContext() {
        return this.context.orElse("");
    }

    public void setContext(Optional<String> newContext) {
        this.context = newContext;
    }

    public LinkedHashMap<ChineseCharacter, KoreanBuild> getHanja() {
        return this.hanja.orElse(new LinkedHashMap<ChineseCharacter, KoreanBuild>());
    }

    public void setHanja(
        Optional<LinkedHashMap<ChineseCharacter, KoreanBuild>> newHanja
    ) {
        this.hanja = newHanja;
    }

    @Override
    public String toString() {
        return String.format(
            """
            [
                Translation=%s
                Entry type=%s
                Meaning=%s
                Context=%s
                Hanja=%s
            ]
            """,
            translation, entryType, meaning, context.orElse(""),
            hanja.orElse(new LinkedHashMap<ChineseCharacter, KoreanBuild>()).toString()
        );
    }

}