package com.stephull.projects.koreanbuildingapp.model;

public interface JamoIterator<T extends Jamo<?>> {
    boolean hasNext();
    T next();
    String getSound();
}
