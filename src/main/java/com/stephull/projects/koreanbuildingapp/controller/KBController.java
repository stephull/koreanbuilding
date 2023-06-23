package com.stephull.projects.koreanbuildingapp.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.stephull.projects.koreanbuildingapp.model.KoreanBuild;
import com.stephull.projects.koreanbuildingapp.repository.KBRepository;

@CrossOrigin(origins="http://127.0.0.1:3000")
@RestController
@RequestMapping("/api/builds")
public class KBController {
    
    @Autowired
    protected KBRepository kbrepo;

    /**
     * Get all available builds, optional containing for all builds with given letter/character
     * @return ResponseEntity<ArrayList<KB>>
     */
    @GetMapping("/")
    public ResponseEntity<ArrayList<KoreanBuild>> getAllBuilds() {
        try {
            ArrayList<KoreanBuild> builds = new ArrayList<KoreanBuild>();
            kbrepo.findAll().forEach(builds::add);
            return (builds.isEmpty())
                ? new ResponseEntity<ArrayList<KoreanBuild>>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<ArrayList<KoreanBuild>>(builds, HttpStatus.OK);
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
    public ResponseEntity<KoreanBuild> getBuildById(
        @PathVariable("id") String id
    ) {
        Optional<KoreanBuild> kbData = kbrepo.findById(id);
        return (kbData.isPresent())
            ? new ResponseEntity<KoreanBuild>(kbData.get(), HttpStatus.OK)
            : new ResponseEntity<KoreanBuild>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/consonant/first/{character}")
    public ResponseEntity<ArrayList<KoreanBuild>> getCharactersByFirstConsonantsCharacter(
        @PathVariable("character") String character,
        @RequestParam Optional<String> firstVowel,
        @RequestParam Optional<String> lastVowel,
        @RequestParam Optional<String> lastConsonant
    ) {
        try {
            ArrayList<KoreanBuild> children = new ArrayList<KoreanBuild>();

            // logic

            return (children.isEmpty())
                ? new ResponseEntity<ArrayList<KoreanBuild>>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<ArrayList<KoreanBuild>>(children, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/vowel/first/{character}")
    public ResponseEntity<ArrayList<KoreanBuild>> getCharactersByFirstVowelCharacter(
        @PathVariable("character") String character,
        @RequestParam Optional<String> firstConsonant,
        @RequestParam Optional<String> lastVowel,
        @RequestParam Optional<String> lastConsonant
    ) {
        try {
            ArrayList<KoreanBuild> children = new ArrayList<KoreanBuild>();

            // logic 

            return (children.isEmpty())
                ? new ResponseEntity<ArrayList<KoreanBuild>>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<ArrayList<KoreanBuild>>(children, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/vowel/second/{character}")
    public ResponseEntity<ArrayList<KoreanBuild>> getCharactersBySecondVowelCharacter(
        @PathVariable("character") String character,
        @RequestParam Optional<String> firstConsonant,
        @RequestParam Optional<String> firstVowel,
        @RequestParam Optional<String> lastConsonant
    ) {
        try {
            ArrayList<KoreanBuild> children = new ArrayList<KoreanBuild>();

            // logic 

            return (children.isEmpty())
                ? new ResponseEntity<ArrayList<KoreanBuild>>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<ArrayList<KoreanBuild>>(children, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/consonant/second/{character}")
    public ResponseEntity<ArrayList<KoreanBuild>> getCharactersByEndingConsonantCharacter(
        @PathVariable("character") String character,
        @RequestParam Optional<String> firstConsonant,
        @RequestParam Optional<String> firstVowel,
        @RequestParam Optional<String> secondVowel
    ) {
        try {
            ArrayList<KoreanBuild> children = new ArrayList<KoreanBuild>();

            // logic 

            return (children.isEmpty())
                ? new ResponseEntity<ArrayList<KoreanBuild>>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<ArrayList<KoreanBuild>>(children, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
