package com.stephull.projects.koreanbuildingapp.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stephull.projects.koreanbuildingapp.model.KoreanBuild;
import com.stephull.projects.koreanbuildingapp.repository.KoreanBuildRepository;

@CrossOrigin(origins="http://127.0.0.1:3000")
@RestController
@RequestMapping("/api/builds")
public class KBController {
    
    protected final KoreanBuildRepository kbrepo;

    public KBController(KoreanBuildRepository kbrepo) {
        this.kbrepo = kbrepo;
    }

    /**
     * Get all available builds, optional containing for all builds with given letter/character
     * @return ResponseEntity<ArrayList<KB>>
     */
    @GetMapping(value="/")
    public ResponseEntity<List<KoreanBuild>> getAllBuilds() {
        try {
            List<KoreanBuild> builds = new ArrayList<KoreanBuild>();
            kbrepo.findAll().forEach(builds::add);
            return (builds.isEmpty())
                ? new ResponseEntity<List<KoreanBuild>>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<List<KoreanBuild>>(builds, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get a specific character or build based on ID
     * @param id
     * @return ResponseEntity<KB>
     */
    @GetMapping(value="/{id}")
    public ResponseEntity<KoreanBuild> getBuildById(
        @PathVariable("id") String id
    ) {
        Optional<KoreanBuild> kbData = kbrepo.findById(id);
        return (kbData.isPresent())
            ? new ResponseEntity<KoreanBuild>(kbData.get(), HttpStatus.OK)
            : new ResponseEntity<KoreanBuild>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value="/consonant/first/{character}")
    public ResponseEntity<List<KoreanBuild>> getCharactersByFirstConsonantsCharacter(
        @PathVariable("character") String character,
        @RequestParam Optional<String> firstVowel,
        @RequestParam Optional<String> lastVowel,
        @RequestParam Optional<String> lastConsonant
    ) {
        try {
            List<KoreanBuild> children = new ArrayList<KoreanBuild>();

            // logic

            return (children.isEmpty())
                ? new ResponseEntity<List<KoreanBuild>>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<List<KoreanBuild>>(children, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="/vowel/first/{character}")
    public ResponseEntity<List<KoreanBuild>> getCharactersByFirstVowelCharacter(
        @PathVariable("character") String character,
        @RequestParam Optional<String> firstConsonant,
        @RequestParam Optional<String> lastVowel,
        @RequestParam Optional<String> lastConsonant
    ) {
        try {
            List<KoreanBuild> children = new ArrayList<KoreanBuild>();

            // logic 

            return (children.isEmpty())
                ? new ResponseEntity<List<KoreanBuild>>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<List<KoreanBuild>>(children, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="/vowel/second/{character}")
    public ResponseEntity<List<KoreanBuild>> getCharactersBySecondVowelCharacter(
        @PathVariable("character") String character,
        @RequestParam Optional<String> firstConsonant,
        @RequestParam Optional<String> firstVowel,
        @RequestParam Optional<String> lastConsonant
    ) {
        try {
            List<KoreanBuild> children = new ArrayList<KoreanBuild>();

            // logic 

            return (children.isEmpty())
                ? new ResponseEntity<List<KoreanBuild>>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<List<KoreanBuild>>(children, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="/consonant/second/{character}")
    public ResponseEntity<List<KoreanBuild>> getCharactersByEndingConsonantCharacter(
        @PathVariable("character") String character,
        @RequestParam Optional<String> firstConsonant,
        @RequestParam Optional<String> firstVowel,
        @RequestParam Optional<String> secondVowel
    ) {
        try {
            List<KoreanBuild> children = new ArrayList<KoreanBuild>();

            // logic 

            return (children.isEmpty())
                ? new ResponseEntity<List<KoreanBuild>>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<List<KoreanBuild>>(children, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
