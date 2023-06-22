package com.stephull.projects.koreanbuildingapp.model;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="ChineseOrigin")
public class ChineseCharacter {
    
    private String character;
    private String unicode;
    private ArrayList<Radical> radicals;
    private int strokeCount;
    private ArrayList<String> definitions;
    private Optional<ArrayList<LinguisticComparison>> comparisons;
    private Optional<Boolean> simplified;

    public ChineseCharacter() {}

    public ChineseCharacter(
        String character,
        String unicode,
        ArrayList<Radical> radicals,
        int strokeCount,
        ArrayList<String> definitions
    ) {
        this.character = character;
        this.unicode = unicode;
        this.radicals = radicals;
        this.strokeCount = strokeCount;
        this.definitions = definitions;
    }

    public ChineseCharacter(
        String character,
        String unicode,
        ArrayList<Radical> radicals,
        int strokeCount,
        ArrayList<String> definitions,
        Optional<ArrayList<LinguisticComparison>> comparisons,
        Optional<Boolean> simplified
    ) {
        this.character = character;
        this.unicode = unicode;
        this.radicals = radicals;
        this.strokeCount = strokeCount;
        this.definitions = definitions;
        this.comparisons = comparisons;
        this.simplified = simplified;
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

    public ArrayList<Radical> getRadicals() {
        return this.radicals;
    }

    public void setRadicals(ArrayList<Radical> newRadicals) {
        this.radicals = newRadicals;
    }

    public int getStrokeCount() {
        return this.strokeCount;
    }

    public void setStrokeCount(int newStrokeCount) {
        this.strokeCount = newStrokeCount;
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

    public void setComparisons(Optional<ArrayList<LinguisticComparison>> newComparisons) {
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
        return String.format(
            """
            [
                Character=%s
                Unicode=%s
                Radicals=%s
                Stroke count=%d
                Definitions=%s
                Comparisons=%s
                Simplified=%b
            ]
            """,
            character, unicode, radicals.toString(), 
            strokeCount, definitions.toString(),
            comparisons.orElse(new ArrayList<LinguisticComparison>()).toString(),
            simplified.orElse(null)
        );
    }

}

class Radical {

    private String radical;
    private String unicode;
    private Optional<String> basic;

    public Radical() {}

    public Radical(
        String radical,
        String unicode
    ) {
        this.radical = radical;
        this.unicode = unicode;
    }

    public Radical(
        String radical,
        String unicode,
        Optional<String> basic
    ) {
        this.radical = radical;
        this.unicode = unicode;
        this.basic = basic;
    }

    public String getRadical() {
        return this.radical;
    }

    public void setRadical(String newRadical) {
        this.radical = newRadical;
    }

    public String getUnicode() {
        return this.unicode;
    }

    public void setUnicode(String newUnicode) {
        this.unicode = newUnicode;
    }

    public String getBasic() {
        return this.basic.orElse("");
    }

    public void setBasic(Optional<String> newBasic) {
        this.basic = newBasic;
    }

    @Override
    public String toString() {
        return String.format(
            """
            [
                Radical=%s
                Unicode=%s
                Basic form=%s
            ]
            """,
            radical, unicode, basic.orElse("")
        );
    }

}

class LinguisticComparison {

    private Language language;
    private AuditoryData sound;
    private String context;

    public LinguisticComparison() {}

    public LinguisticComparison(
        Language language, 
        AuditoryData sound,
        String context
    ) {
        this.language = language;
        this.sound = sound;
        this.context = context;
    }

    public Language getLanguage() {
        return this.language;
    }

    public void setLanguage(Language newLanguage) {
        this.language = newLanguage;
    }

    public AuditoryData getSound() {
        return this.sound;
    }

    public void setSound(AuditoryData newSound) {
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
        return String.format(
            """
            [
                Language=%s
                Sound=%s
                Context=%s
            ]     
            """,
            language.toString(), sound.toString(), context
        );
    }

}