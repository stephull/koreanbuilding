package com.stephull.projects.koreanbuildingapp.koreanbuilding;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.stephull.projects.koreanbuildingapp.model.KoreanSpeechCluster;
import com.stephull.projects.koreanbuildingapp.model.SpeechType;

@Component
public class KBAppRunner implements CommandLineRunner {
    
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void run(String... args) throws Exception {
        String a = args[0];
        if (args.length < 1 || a.equalsIgnoreCase("query")) {
            doQuery();
        } else if (a.equalsIgnoreCase("insert")) {
            doInsert();
        } else if (a.equalsIgnoreCase("update")) {
            doUpdate();
        } else if (a.equalsIgnoreCase("delete")) {
            doDelete();
        } else {
            throw new IllegalArgumentException("Illegal program argument: " + a);
        }
    }

    private void doQuery() {

        // one speech cluster
        Criteria criteria = Criteria.where("romanization").is("ng");
        Query query = Query.query(criteria);
        KoreanSpeechCluster ksc = mongoTemplate.findOne(query, KoreanSpeechCluster.class);
        System.out.println("One type query: " + ksc + '\n');

        // all speech clusters
        List<KoreanSpeechCluster> kscs = mongoTemplate.findAll(KoreanSpeechCluster.class);
        System.out.println("Query for all speech clusters: ");
        kscs.forEach(System.out::println);
        System.out.println('\n');

        // query many clusters by type
        BasicQuery queryByType = new BasicQuery(" { type: 'vowel' } ");
        List<KoreanSpeechCluster> vowels = mongoTemplate.find(queryByType, KoreanSpeechCluster.class);
        System.out.println("Query by type, clusters: ");
        vowels.forEach(System.out::println);
        System.out.println("\n");

    }

    private void doInsert() {

        // insert a sample of Korean speech clusters 
        // (3 vowels, 3 consonants, and 3 final consonants)
        KoreanSpeechCluster constant1 = new KoreanSpeechCluster(
            "ㄱ", "g", SpeechType.CONSONANT
        );
        KoreanSpeechCluster constant2 = new KoreanSpeechCluster(
            "ㄴ", "n", SpeechType.CONSONANT
        );
        KoreanSpeechCluster constant3 = new KoreanSpeechCluster(
            "ㄷ", "d", SpeechType.CONSONANT
        );
        KoreanSpeechCluster endconstant1 = new KoreanSpeechCluster(
            "ㄱ", "k", SpeechType.END_CONSONANT
        );
        KoreanSpeechCluster endconstant2 = new KoreanSpeechCluster(
            "ㄷ", "t", SpeechType.END_CONSONANT
        );
        KoreanSpeechCluster endconstant3 = new KoreanSpeechCluster(
            "ㅇ", "ng", SpeechType.END_CONSONANT
        );
        KoreanSpeechCluster vowel1 = new KoreanSpeechCluster(
            "ㅏ", "ah", SpeechType.VOWEL
        );
        KoreanSpeechCluster vowel2 = new KoreanSpeechCluster(
            "ㅓ", "uh", SpeechType.VOWEL
        );
        KoreanSpeechCluster vowel3 = new KoreanSpeechCluster(
            "ㅠ", "yoo", SpeechType.VOWEL
        );

        // insert one at a time
        mongoTemplate.insert(constant1);
        mongoTemplate.insert(constant2);
        mongoTemplate.insert(constant3);

        // insert many
        List<KoreanSpeechCluster> clusters = Arrays.asList(
            endconstant1,
            endconstant2,
            endconstant3,
            vowel1,
            vowel2,
            vowel3
        );
        mongoTemplate.insert(clusters, KoreanSpeechCluster.class);

    }

    private void doUpdate() {

        //

    }

    private void doDelete() {

        //

    }

}
