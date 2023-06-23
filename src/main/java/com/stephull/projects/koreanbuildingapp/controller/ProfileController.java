package com.stephull.projects.koreanbuildingapp.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.stephull.projects.koreanbuildingapp.model.Profile;
import com.stephull.projects.koreanbuildingapp.repository.ProfileRepository;

@CrossOrigin(origins="http://127.0.0.1:3000")
@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    @Autowired
    protected ProfileRepository profRepo;

    @GetMapping("/")
    public ResponseEntity<ArrayList<Profile>> getAllProfiles(
        @RequestParam(required=false) String username
    ) {
        try {
            ArrayList<Profile> profiles = new ArrayList<Profile>();
            if (username != null) {
                profRepo.findByUsername(username).forEach(profiles::add);
            }
            else {
                profRepo.findAll().forEach(profiles::add);
            }
            return (profiles.isEmpty())
                ? new ResponseEntity<ArrayList<Profile>>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<ArrayList<Profile>>(profiles, HttpStatus.OK); 
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profile> getProfileById(
        @PathVariable("id") String id
    ) {
        Optional<Profile> profData = profRepo.findById(id);
        return (profData.isPresent())
            ? new ResponseEntity<Profile>(profData.get(), HttpStatus.OK)
            : new ResponseEntity<Profile>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/stats/{level}")
    public ResponseEntity<ArrayList<Profile>> getProfileStatsByLevel(
        @PathVariable("level") int level
    ) {
        try {
            ArrayList<Profile> profiles = new ArrayList<Profile>();
            profRepo.findByLevel(level).forEach(profiles::add);
            return (profiles.isEmpty()) 
                ? new ResponseEntity<ArrayList<Profile>>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<ArrayList<Profile>>(profiles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
