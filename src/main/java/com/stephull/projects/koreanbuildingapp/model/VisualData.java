package com.stephull.projects.koreanbuildingapp.model;

import java.io.File;

public class VisualData {
    
    private File file;
    private String source;
    private String description;

    public VisualData() {}

    public VisualData(
        File file,
        String source,
        String description
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
        return this.description;
    }

    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    @Override
    public String toString() {
        String ret = String.format(
            """
            [
                Image file (path)=%s
                Source=%s
                Description=%s
            ]     
            """,
            file.getAbsolutePath().toString(), 
            source, description
        );
        return ret.indent(2);
    }

}
