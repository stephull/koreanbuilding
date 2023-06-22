package com.stephull.projects.koreanbuildingapp.model;

import java.io.File;
import java.util.Optional;

public class VisualData {
    
    private File imageFile;
    private String source;
    private Optional<String> description;

    public VisualData() {}

    public VisualData(
        File imageFile,
        String source
    ) {
        this.imageFile = imageFile;
        this.source = source;
    }

    public VisualData(
        File imageFile,
        String source,
        Optional<String> description
    ) {
        this.imageFile = imageFile;
        this.source = source;
        this.description = description;
    }

    public File getImageFile() {
        return this.imageFile;
    }

    public void setImageFile(File newFile) {
        this.imageFile = newFile;
    }

    public String getSource() {
        return this.source;
    }

    public void setSource(String newSource) {
        this.source = newSource;
    }

    public String getDescription() {
        return this.description.orElse("");
    }

    public void setDescription(Optional<String> newDescription) {
        this.description = newDescription;
    }

    @Override
    public String toString() {
        return String.format(
            """
            [
                Image file (path)=%s
                Source=%s
                Description=%s
            ]     
            """,
            imageFile.getAbsolutePath(), source,
            description.orElse("")
        );
    }

}
