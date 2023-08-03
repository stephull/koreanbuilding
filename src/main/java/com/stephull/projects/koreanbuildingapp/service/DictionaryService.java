package com.stephull.projects.koreanbuildingapp.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stephull.projects.koreanbuildingapp.model.ChineseOrigin;
import com.stephull.projects.koreanbuildingapp.model.DictionaryDefinition;
import com.stephull.projects.koreanbuildingapp.model.KoreanBuild;
import com.stephull.projects.koreanbuildingapp.model.WordType;
import com.stephull.projects.koreanbuildingapp.repository.KorEngDictRepository;

@Service
public class DictionaryService {

    private KorEngDictRepository kedrepo;

    private final String hanjaKeyword = "_hanja_";

    private boolean includesEtymology(String source) {
        return source.contains(HTMLPropertyType.ETYMOLOGY);
    }

    private boolean includesHanja(String source) {
        return source.contains(hanjaKeyword);
    }

    /**
     * 
     * @param source
     * @return WordType
     */
    private WordType findDefinitionKeyTerms(String source) {
        if (source.contains(HTMLPropertyType.ADVERB)) {
            return WordType.ADVERB;
        } else if (source.contains(HTMLPropertyType.ADJECTIVE)) {
            return WordType.ADJECTIVE;
        } else if (source.contains(HTMLPropertyType.NOUN)) {
            return WordType.NOUN;
        } else if (source.contains(HTMLPropertyType.PROPER_NOUN)) {
            return WordType.PROPER_NOUN;
        } else if (source.contains(HTMLPropertyType.VERB)) {
            return WordType.VERB;
        }
        return null;
    }

    /**
     * 
     * @param props
     * @return List<DictionaryDefinition>
     */
    public List<DictionaryDefinition> convertPropertiesToDefinitions(Map<String, Map<String, String>> props) {
        List<DictionaryDefinition> newList = new ArrayList<DictionaryDefinition>();
        
        /*int sectionCount = 0;

        for (Map.Entry<String, Map<String, String>> entry : props.entrySet()) {
            String key = entry.getKey();
            
            if (this.includesEtymology(key)) {
                if (sectionCount > 0) {
                    newList.add(current);
                } else {

                }
                current = new DictionaryDefinition();
                sectionCount++;

                boolean hasHanja = this.includesHanja(key);
                
            } else {

            }

            WordType wt = this.findDefinitionKeyTerms(key);
            if (wt != null) {

            }


            Map<String, String> inner = entry.getValue();
            for (Map.Entry<String, String> contents : inner.entrySet()) {
                String classname = contents.getKey(), content = contents.getValue();
            }
        }*/

        return newList;
    }

    /**
     * This method takes each definition and returns any words that are hyperlinked to other words
     * Users can be redirected to the page of the Korean word and/or the specifically selected
     *      Korean character that is being selected
     * @param props
     * @return List<List<KoreanBuild>>
     */
    public List<List<KoreanBuild>> convertPropertiesOfLinksToKoreanBuilds(Map<String, Map<String, String>> props) {
        List<List<KoreanBuild>> newList = new ArrayList<List<KoreanBuild>>();

        for (Map.Entry<String, Map<String, String>> entry : props.entrySet()) {
            String key = entry.getKey();
            Map<String, String> inner = entry.getValue();

            for (Map.Entry<String, String> contents : inner.entrySet()) {
                List<KoreanBuild> contentBuilds = new ArrayList<KoreanBuild>();
                
                String classname = contents.getKey(), content = contents.getValue();

                newList.add(contentBuilds);
            }
        }

        return newList;
    }

}
