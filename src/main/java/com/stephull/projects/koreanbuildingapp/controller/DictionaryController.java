package com.stephull.projects.koreanbuildingapp.controller;

//import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stephull.projects.koreanbuildingapp.model.DictionaryDefinition;
import com.stephull.projects.koreanbuildingapp.model.KorEngDictionary;
import com.stephull.projects.koreanbuildingapp.repository.KorEngDictRepository;
import com.stephull.projects.koreanbuildingapp.service.WikimediaHTMLConversion;

@CrossOrigin(origins="http://127.0.0.1:3000")
@RestController
@RequestMapping("/api/dictionary")
public class DictionaryController {
    
    @Autowired
    protected KorEngDictRepository kedrepo;

    @Autowired
    protected WikimediaHTMLConversion whc;

    @Autowired
    protected MongoTemplate mongoTemplate;

    @GetMapping("/html/{query}")
    public ResponseEntity<KorEngDictionary> getHTMLFile(@PathVariable String query) {
        try {

            // check if already in database
            Criteria criteria = Criteria.where("entry").is(query);
            KorEngDictionary storedContent = mongoTemplate.findOne(
                new Query(criteria), KorEngDictionary.class
            );
            if (storedContent != null) return ResponseEntity.ok(storedContent);

            // else: apply encoding rules to URL before fetching HTML result
            String encodedQuery = whc.encodeURIPercentCode(query);
            //String cdf = query + ".html";
            String url = "https://en.wiktionary.org/api/rest_v1/page/html/" + encodedQuery;
           
            Document doc = Jsoup.connect(url).get();
            Map<String, Map<String, String>> props = whc.fetchPropertiesFromWikimediaHTML(doc);
            System.out.println("TEST: " + props);

            // inscribe new dictionary entry to MongoDB
            KorEngDictionary newKED = new KorEngDictionary();
            newKED.setEntry(query);
            //newKED.setDefinitions();
            //newKED.setAssociatedBuilds();
            //newKED.setSound();
            mongoTemplate.save(newKED);

            return ResponseEntity.ok(newKED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/define/{query}")
    public ResponseEntity<List<DictionaryDefinition>> getDefinitionsListByQuery(@PathVariable String query) {
        try {
            ResponseEntity<KorEngDictionary> responseEntity = this.getHTMLFile(query);
            KorEngDictionary ked = responseEntity.getBody();
            return (ked != null) 
                ? ResponseEntity.ok(ked.getDefinitions())
                : new ResponseEntity<List<DictionaryDefinition>>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
