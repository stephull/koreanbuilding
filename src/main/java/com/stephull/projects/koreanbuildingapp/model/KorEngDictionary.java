package com.stephull.projects.koreanbuildingapp.model;

import java.util.List;
//import java.util.Optional;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="KorEngDictionary")
public class KorEngDictionary {
    
    @Id 
    @Field("_id")
    private String id;

    @Field("kedid")
    private CustomID<KorEngDictionary> kedid;
    
    private String entry;
    private List<List<KoreanBuild>> relatedBuilds;
    private List<DictionaryDefinition> definitions;
    //private Optional<VisualData> image;

    public KorEngDictionary() {}

    public KorEngDictionary(
        String entry,
        List<List<KoreanBuild>> relatedBuilds,
        List<DictionaryDefinition> definitions
    ) {
        this.entry = entry;
        this.relatedBuilds = relatedBuilds;
        this.definitions = definitions;
    }

    /*public KorEngDictionary(
        String entry,
        List<List<KoreanBuild>> relatedBuilds,
        List<DictionaryDefinition> definitions,
        VisualData image
    ) {
        this.entry = entry;
        this.relatedBuilds = relatedBuilds;
        this.definitions = definitions;
        this.image = Optional.ofNullable(image);
    }*/

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

    public List<List<KoreanBuild>> getRelatedBuilds() {
        return this.relatedBuilds;
    }

    public void setRelatedBuilds(List<List<KoreanBuild>> newRelatedBuilds) {
        this.relatedBuilds = newRelatedBuilds;
    }

    public List<DictionaryDefinition> getDefinitions() {
        return this.definitions;
    }

    public void setDefinitions(List<DictionaryDefinition> newDefinitions) {
        this.definitions = newDefinitions;
    }

    /*public VisualData getImage() {
        return this.image.orElse(null);
    }

    public void setImage(Optional<VisualData> newImage) {
        this.image = newImage;
    }*/

    @Override
    public String toString() {
        String ret = String.format(
            """
            [
                ID=%s
                Entry=%s
                Definitions=%s
                Image data=%s
            ]        
            """,
            id, entry, definitions //, image
        );
        return ret.indent(2);
    }

}