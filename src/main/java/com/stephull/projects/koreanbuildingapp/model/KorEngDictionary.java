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

    private CustomID<KorEngDictionary> kedid;
    private String entry;
    private ArrayList<CustomID<KoreanBuild>> associatedBuilds;
    private ArrayList<DictionaryDefinition> definitions;
    private AuditoryData sound;
    private Optional<VisualData> image;

    public KorEngDictionary() {}

    public KorEngDictionary(
        String entry,
        ArrayList<CustomID<KoreanBuild>> associatedBuilds,
        ArrayList<DictionaryDefinition> definitions,
        AuditoryData sound
    ) {
        this.entry = entry;
        this.associatedBuilds = associatedBuilds;
        this.definitions = definitions;
        this.sound = sound;
    }

    public KorEngDictionary(
        String entry,
        ArrayList<CustomID<KoreanBuild>> associatedBuilds,
        ArrayList<DictionaryDefinition> definitions,
        AuditoryData sound,
        VisualData image
    ) {
        this.entry = entry;
        this.associatedBuilds = associatedBuilds;
        this.definitions = definitions;
        this.sound = sound;
        this.image = Optional.ofNullable(image);
    }

    public String getId() {
        return this.id;
    }

    public String getKedId() {
        return this.kedid.getCustomID();
    }

    public void setKedId(String newKedId) {
        this.kedid.setCustomID(newKedId);
    }

    public String getEntry() {
        return this.entry;
    }

    public void setEntry(String newEntry) {
        this.entry = newEntry;
    }

    public ArrayList<CustomID<KoreanBuild>> getAssociatedBuilds() {
        return this.associatedBuilds;
    }

    public void setAssociatedBuilds(ArrayList<CustomID<KoreanBuild>> newAssociatedBuilds) {
        this.associatedBuilds = newAssociatedBuilds;
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
        String ret = String.format(
            """
            [
                ID=%s
                Entry=%s
                Definitions=%s
                Sound data=%s
                Image data=%s
            ]        
            """,
            id, entry, definitions, sound, image
        );
        return ret.indent(2);
    }

}

class DictionaryDefinition {

    private String translation;
    private DictionaryEntryType entryType;    // noun, verb, adverb, etc.
    private String meaning;
    private Optional<String> context;   // a.k.a examples
    private Optional<LinkedHashMap<KoreanBuild, ChineseOrigin>> hanja;

    public String getTranslation() {
        return this.translation;
    }

    public void setTranslation(String newTranslation) {
        this.translation = newTranslation;
    }

    public DictionaryEntryType getEntryType() {
        return this.entryType;
    }

    public void setEntryType(DictionaryEntryType newEntryType) {
        this.entryType = newEntryType;
    }

    public String getMeaning() {
        return this.meaning;
    }

    public void setMeaning(String newMeaning) {
        this.meaning = newMeaning;
    }

    public String getContext() {
        return this.context.orElse("None");
    }

    public void setContext(Optional<String> newContext) {
        this.context = newContext;
    }

    public LinkedHashMap<KoreanBuild, ChineseOrigin> getHanja() {
        return this.hanja.orElse(new LinkedHashMap<KoreanBuild, ChineseOrigin>());
    }

    public void setHanja(
        Optional<LinkedHashMap<KoreanBuild, ChineseOrigin>> newHanja
    ) {
        this.hanja = newHanja;
    }

    @Override
    public String toString() {
        String ret = String.format(
            """
            [
                Translation=%s
                Entry type=%s
                Meaning=%s
                Context=%s
                Hanja=%s
            ]
            """,
            translation, entryType, meaning, context, hanja
        );
        return ret.indent(2);
    }

}