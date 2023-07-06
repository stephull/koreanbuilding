package com.stephull.projects.koreanbuildingapp.model;

public class CustomID<T> {
    
    private String id;
    private Class<T> association;

    public CustomID(
        String id, Class<T> association
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

    public Class<T> getAssocation() {
        return this.association;
    }

    public void setAssocation(Class<T> newAssociation) {
        this.association = newAssociation;
    }
}