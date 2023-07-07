package com.stephull.projects.koreanbuildingapp.model;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="ChineseOrigin")
public class ChineseOrigin {
    
    @Id
    private String id;

    private CustomID<ChineseOrigin> coid;
    private String character;
    private String unicode;
    private ArrayList<String> definitions;
    private Optional<ArrayList<LinguisticComparison>> comparisons;
    private Optional<Boolean> simplified;

    public ChineseOrigin() {}

    public ChineseOrigin(
        String character,
        String unicode,
        int strokeCount,
        ArrayList<String> definitions
    ) {
        this.character = character;
        this.unicode = unicode;
        this.definitions = definitions;
    }

    public ChineseOrigin(
        String character,
        String unicode,
        int strokeCount,
        ArrayList<String> definitions,
        ArrayList<LinguisticComparison> comparisons,
        Boolean simplified
    ) {
        this.character = character;
        this.unicode = unicode;
        this.definitions = definitions;
        this.comparisons = Optional.ofNullable(comparisons);
        this.simplified = Optional.ofNullable(simplified);
    }

    public String getId() {
        return this.id;
    }

    public String getCoId() {
        return this.coid.getCustomID();
    }

    public void setCoId(String newCoId) {
        this.coid.setCustomID(newCoId);
    }

    public String getCharacter() {
        return this.character;
    }

    public void setCharacter(String newCharacter) {
        this.character = newCharacter;
    }

    public String getUnicode() {
        return this.unicode;
    }

    public void setUnicode(String newUnicode) {
        this.unicode = newUnicode;
    }

    public ArrayList<String> getDefinitions() {
        return this.definitions;
    }

    public void setDefinitions(ArrayList<String> newDefinitions) {
        this.definitions = newDefinitions;
    }

    public ArrayList<LinguisticComparison> getComparisons() {
        return this.comparisons.orElse(new ArrayList<LinguisticComparison>());
    }

    public void setComparisons(
        Optional<ArrayList<LinguisticComparison>> newComparisons
    ) {
        this.comparisons = newComparisons;
    }

    public Boolean getSimplifiedStatus() {
        return this.simplified.orElse(null);
    }

    public void setSimplifiedStatus(Optional<Boolean> newSimplifiedStatus) {
        this.simplified = newSimplifiedStatus;
    }

    @Override
    public String toString() {
        String ret = String.format(
            """
            [
                Character=%s
                Unicode=%s
                Definitions=%s
                Comparisons=%s
                Simplified=%b
            ]
            """,
            character, 
            unicode, 
            definitions,
            comparisons,
            simplified
        );
        return ret.indent(2);
    }

}

class LinguisticComparison {

    private LanguageType language;
    private String context;
    private Optional<AuditoryData> sound;

    public LinguisticComparison() {}

    public LinguisticComparison(
        LanguageType language, 
        String context
    ) {
        this.language = language;
        this.context = context;
    }

    public LinguisticComparison(
        LanguageType language,
        String context,
        Optional<AuditoryData> sound
    ) {
        this.language = language;
        this.context = context;
        this.sound = sound;
    }

    public LanguageType getLanguage() {
        return this.language;
    }

    public void setLanguage(LanguageType newLanguage) {
        this.language = newLanguage;
    }

    public AuditoryData getSound() {
        return this.sound.orElse(null);
    }

    public void setSound(Optional<AuditoryData> newSound) {
        this.sound = newSound;
    }

    public String getContext() {
        return this.context;
    }

    public void setContext(String newContext) {
        this.context = newContext;
    }

    @Override
    public String toString() {
        String ret = String.format(
            """
            [
                Language=%s
                Context=%s
                Sound=%s
            ]     
            """,
            language, context, sound
        );
        return ret.indent(2);
    }

}