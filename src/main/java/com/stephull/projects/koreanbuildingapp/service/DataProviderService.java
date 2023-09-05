package com.stephull.projects.koreanbuildingapp.service;

import com.mongodb.client.MongoDatabase;

public interface DataProviderService<T> {
    public boolean isCollectionEmpty();
    public void addObjectToCollection(MongoDatabase database, T object);
    public void executeAllDocumentCreation();
}
