package com.stephull.projects.koreanbuildingapp.model;

import java.io.File;

public class AuditoryData {
    
    private File soundFile;
    private boolean completeSentence;
    private String source;

    public AuditoryData() {}

    public AuditoryData(
        File soundFile,
        boolean completeSentence,
        String source
    ) {
        this.soundFile = soundFile;
        this.completeSentence = completeSentence;
        this.source = source;
    }

    public File getSoundFile() {
        return this.soundFile;
    }

    public void setSoundFile(File newFile) {
        this.soundFile = newFile;
    }

    public boolean getCompleteSentenceStatus() {
        return this.completeSentence;
    }

    public void setCompleteSentenceStatus(boolean newStatus) {
        this.completeSentence = newStatus;
    }

    public String getSource() {
        return this.source;
    }

    public void setSource(String newSource) {
        this.source = newSource;
    }

    @Override
    public String toString() {
        return String.format(
            """
            [
                Audio file (path)=%s
                Complete sentence=%b
                Source=%s
            ] 
            """,
            soundFile.getAbsolutePath(), completeSentence, source
        );
    }

}
