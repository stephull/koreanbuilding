package com.stephull.projects.koreanbuildingapp.model;

import java.util.LinkedHashMap;
import java.util.Optional;

public class DictionaryDefinition {

    private String translation;
    private WordType entryType;    // noun, verb, adverb, etc.
    private String meaning;
    private Optional<String> context; 
    private Optional<LinkedHashMap<KoreanBuild, ChineseOrigin>> hanja;

    public DictionaryDefinition() {}

    public DictionaryDefinition(
        String translation,
        WordType entryType,
        String meaning
    ) {
        this.translation = translation;
        this.entryType = entryType;
        this.meaning = meaning;
    }

    public DictionaryDefinition(
        String translation,
        WordType entryType,
        String meaning,
        String context,
        LinkedHashMap<KoreanBuild, ChineseOrigin> hanja
    ) {
        this.translation = translation;
        this.entryType = entryType;
        this.meaning = meaning;
        this.context = Optional.ofNullable(context);
        this.hanja = Optional.ofNullable(hanja);
    }

    public String getTranslation() {
        return this.translation;
    }

    public void setTranslation(String newTranslation) {
        this.translation = newTranslation;
    }

    public WordType getEntryType() {
        return this.entryType;
    }

    public void setEntryType(WordType newEntryType) {
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
