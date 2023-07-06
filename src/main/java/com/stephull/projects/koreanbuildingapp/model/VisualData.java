package com.stephull.projects.koreanbuildingapp.model;

import java.io.File;
import java.util.Optional;

public class VisualData {
    
    private File file;
    private String source;
    private Optional<String> description;

    public VisualData() {}

    public VisualData(
        File file,
        String source
    ) {
        this.file = file;
        this.source = source;
    }

    public VisualData(
        File file,
        String source,
        Optional<String> description
    ) {
        this.file = file;
        this.source = source;
        this.description = description;
    }

    public File getFile() {
        return this.file;
    }

    public void setFile(File newFile) {
        this.file = newFile;
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
            file.getAbsolutePath(), source,
            description.orElse("None")
        );
    }

}
