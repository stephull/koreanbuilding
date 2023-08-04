package com.stephull.projects.koreanbuildingapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stephull.projects.koreanbuildingapp.model.KoreanSpeechCluster;
import com.stephull.projects.koreanbuildingapp.repository.SpeechRepository;

@CrossOrigin(origins="http://127.0.0.1:3000")
@RestController
@RequestMapping("/api/speech")
public class SpeechController {
    
    protected final SpeechRepository speechrepo;

    @Autowired public SpeechController(SpeechRepository speechrepo) {
        this.speechrepo = speechrepo;
    }

    @GetMapping(value="/")
    public ResponseEntity<List<KoreanSpeechCluster>> getAllSpeechClusters() {
        try {
            List<KoreanSpeechCluster> clusters = new ArrayList<KoreanSpeechCluster>();
            speechrepo.findAll().forEach(clusters::add);
            return (clusters.isEmpty())
                ? new ResponseEntity<List<KoreanSpeechCluster>>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<List<KoreanSpeechCluster>>(clusters, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
