package com.stephull.projects.koreanbuildingapp.model;

import java.util.Optional;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Profiles")
public class Profile {

    @Id
    private String id;

    private String username;
    private String pin;     //
    private int level;
    private int daysActive;
    private Optional<Integer> fluencyScore;
    private Optional<String> about;

}