package com.stephull.projects.koreanbuildingapp.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="KorEngDictionary")
public class KorEngDictionary {
    
    @Id
    private String id;

    private CustomID<KorEngDictionary> kedid;
    private String entry;
    private List<KoreanBuild> associatedBuilds;
    private List<DictionaryDefinition> definitions;
    private Optional<AuditoryData> sound;
    private Optional<VisualData> image;

    public KorEngDictionary() {}

    public KorEngDictionary(
        String entry,
        List<KoreanBuild> associatedBuilds,
        List<DictionaryDefinition> definitions
    ) {
        this.entry = entry;
        this.associatedBuilds = associatedBuilds;
        this.definitions = definitions;
    }

    public KorEngDictionary(
        String entry,
        List<KoreanBuild> associatedBuilds,
        List<DictionaryDefinition> definitions,
        AuditoryData sound,
        VisualData image
    ) {
        this.entry = entry;
        this.associatedBuilds = associatedBuilds;
        this.definitions = definitions;
        this.sound = Optional.ofNullable(sound);
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

    public List<KoreanBuild> getAssociatedBuilds() {
        return this.associatedBuilds;
    }

    public void setAssociatedBuilds(List<KoreanBuild> newAssociatedBuilds) {
        this.associatedBuilds = newAssociatedBuilds;
    }

    public List<DictionaryDefinition> getDefinitions() {
        return this.definitions;
    }

    public void setDefinitions(List<DictionaryDefinition> newDefinitions) {
        this.definitions = newDefinitions;
    }

    public AuditoryData getSound() {
        return this.sound.orElse(null);
    }

    public void setSound(Optional<AuditoryData> newSound) {
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