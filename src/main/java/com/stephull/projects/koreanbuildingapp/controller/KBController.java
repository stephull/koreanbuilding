package com.stephull.projects.koreanbuildingapp.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.stephull.projects.koreanbuildingapp.model.KB;
import com.stephull.projects.koreanbuildingapp.repository.KBRepository;

@CrossOrigin(origins="http://127.0.0.1:3000")
@RestController
@RequestMapping("/api/builds")
public class KBController {
    
    @Autowired
    protected KBRepository kbrepo;

    /**
     * Get all available builds, optional containing for all builds with given letter/character
     * @return ResponseEntity<List<KB>>
     */
    @GetMapping("/")
    public ResponseEntity<List<KB>> getAllBuilds() {
        try {
            List<KB> builds = new ArrayList<KB>();
            kbrepo.findAll().forEach(builds::add);
            return (builds.isEmpty())
                ? new ResponseEntity<List<KB>>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<List<KB>>(builds, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get a specific character or build based on ID
     * @param id
     * @return ResponseEntity<KB>
     */
    @GetMapping("/{id}")
    public ResponseEntity<KB> getBuildById(
        @PathVariable("id") long id
    ) {
        Optional<KB> kbData = kbrepo.findById(id);
        return (kbData.isPresent())
            ? new ResponseEntity<KB>(kbData.get(), HttpStatus.OK)
            : new ResponseEntity<KB>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/consonant/first/{character}")
    public ResponseEntity<List<KB>> getCharactersByFirstConsonantsCharacter(
        @PathVariable("character") String character,
        @RequestParam Optional<String> firstVowel,
        @RequestParam Optional<String> lastVowel,
        @RequestParam Optional<String> lastConsonant
    ) {
        try {
            List<KB> children = new ArrayList<KB>();

            // logic

            return (children.isEmpty())
                ? new ResponseEntity<List<KB>>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<List<KB>>(children, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/vowel/first/{character}")
    public ResponseEntity<List<KB>> getCharactersByFirstVowelCharacter(
        @PathVariable("character") String character,
        @RequestParam Optional<String> firstConsonant,
        @RequestParam Optional<String> lastVowel,
        @RequestParam Optional<String> lastConsonant
    ) {
        try {
            List<KB> children = new ArrayList<KB>();

            // logic 

            return (children.isEmpty())
                ? new ResponseEntity<List<KB>>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<List<KB>>(children, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/vowel/second/{character}")
    public ResponseEntity<List<KB>> getCharactersBySecondVowelCharacter(
        @PathVariable("character") String character,
        @RequestParam Optional<String> firstConsonant,
        @RequestParam Optional<String> firstVowel,
        @RequestParam Optional<String> lastConsonant
    ) {
        try {
            List<KB> children = new ArrayList<KB>();

            // logic 

            return (children.isEmpty())
                ? new ResponseEntity<List<KB>>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<List<KB>>(children, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/consonant/second/{character}")
    public ResponseEntity<List<KB>> getCharactersByEndingConsonantCharacter(
        @PathVariable("character") String character,
        @RequestParam Optional<String> firstConsonant,
        @RequestParam Optional<String> firstVowel,
        @RequestParam Optional<String> secondVowel
    ) {
        try {
            List<KB> children = new ArrayList<KB>();

            // logic 

            return (children.isEmpty())
                ? new ResponseEntity<List<KB>>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<List<KB>>(children, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
