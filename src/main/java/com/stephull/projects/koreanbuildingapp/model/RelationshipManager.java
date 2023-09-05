package com.stephull.projects.koreanbuildingapp.model;

import java.util.Map;

public interface RelationshipManager<T> {

    public void addRelationship(T key, T value);
    
    public Map<T, T> getRelationships();

}