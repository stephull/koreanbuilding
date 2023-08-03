package com.stephull.projects.koreanbuildingapp.controller;

import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stephull.projects.koreanbuildingapp.koreanbuilding.AppPropertyConfig;
import com.stephull.projects.koreanbuildingapp.model.DictionaryDefinition;
import com.stephull.projects.koreanbuildingapp.model.KorEngDictionary;
import com.stephull.projects.koreanbuildingapp.model.KoreanBuild;
import com.stephull.projects.koreanbuildingapp.repository.KorEngDictRepository;
import com.stephull.projects.koreanbuildingapp.service.DictionaryService;
import com.stephull.projects.koreanbuildingapp.service.KoreanLetterConversion;
import com.stephull.projects.koreanbuildingapp.service.WikiHTMLConversion;

@CrossOrigin(origins="http://127.0.0.1:3000")
@RestController
@RequestMapping("/api/dictionary")
public class DictionaryController {

    private final AppPropertyConfig appPropertyConfig;
    
    public DictionaryController(AppPropertyConfig appPropertyConfig) {
        this.appPropertyConfig = appPropertyConfig;
    }

    protected KorEngDictRepository kedrepo;
    protected DictionaryService kedservice;
    protected WikiHTMLConversion whc;
    protected KoreanLetterConversion klc;
    protected MongoTemplate mongoTemplate;

    @GetMapping(value="/")
    public ResponseEntity<List<KorEngDictionary>> getAllEntries() {
        try {
            List<KorEngDictionary> list = kedrepo.findAll();
            return (list != null) 
                ? ResponseEntity.ok(list)
                : new ResponseEntity<List<KorEngDictionary>>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="/html/{query}")
    public ResponseEntity<KorEngDictionary> getDictionaryEntry(@PathVariable("query") String query) {
        try {

            // check if already in database
            Criteria criteria = Criteria.where("entry").is(query);
            KorEngDictionary storedContent = mongoTemplate.findOne(
                new Query(criteria), KorEngDictionary.class
            );
            return (storedContent != null)
                ? ResponseEntity.ok(storedContent)
                : this.createNewEntryFromHTML(query);
            
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping(value="/html/{query}/create")
    private ResponseEntity<KorEngDictionary> createNewEntryFromHTML(@PathVariable("query") String query) {
        try {
            String encodedQuery = whc.encodeURIPercentCode(query);

            String htmlApi = appPropertyConfig.getConfigValue("htmlapi.baseendpoint");
            String url = htmlApi + encodedQuery;
           
            Document doc = Jsoup.connect(url).get();
            Map<String, Map<String, String>> props = whc.fetchPropertiesFromHTML(doc);

            // inscribe new dictionary entry to MongoDB
            KorEngDictionary newKED = new KorEngDictionary();
            newKED.setEntry(query);

            List<DictionaryDefinition> defList = kedservice.convertPropertiesToDefinitions(props);
            newKED.setDefinitions((defList != null) ? defList : null);

            List<List<KoreanBuild>> buildList = kedservice.convertPropertiesOfLinksToKoreanBuilds(props);
            newKED.setRelatedBuilds((buildList != null) ? buildList : null);

            KorEngDictionary compare = mongoTemplate.save(newKED);

            return (newKED.equals(compare)) 
                ? ResponseEntity.ok(compare)
                : new ResponseEntity<KorEngDictionary>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="/define/{query}")
    public ResponseEntity<List<DictionaryDefinition>> getDefinitionsListByQuery(@PathVariable String query) {
        try {
            ResponseEntity<KorEngDictionary> responseEntity = this.createNewEntryFromHTML(query);
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
