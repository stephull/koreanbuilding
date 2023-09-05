package com.stephull.projects.koreanbuildingapp.model;

public class CustomID<T> {
    
    private String id;
    private String association;

    public CustomID(
        String id, String association
    ) {
        this.id = id;
        this.association = association;
    }

    public String getCustomID() {
        return this.id;
    }

    public void setCustomID(String newID) {
        this.id = newID;
    }

    public String getAssocation() {
        return this.association;
    }

    public void setAssocation(String newAssociation) {
        this.association = newAssociation;
    }
}