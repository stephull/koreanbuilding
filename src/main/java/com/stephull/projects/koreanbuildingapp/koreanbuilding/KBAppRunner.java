package com.stephull.projects.koreanbuildingapp.koreanbuilding;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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
        System.out.println("\nHello! Pick a number between 1 to 2, for the following options:");
        System.out.println("1) Query test on Korean cluster");
        System.out.println("2) Create Korean cluster");
        System.out.println(":: Non-options will result in an exception. ::");
        System.out.print(">>> ");

        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        sc.close();

        if (option < 1 || option > 2) throw new IllegalArgumentException();

        switch (option) {
            case 1:
                doQuery();
                break;
            case 2:
                doInsert();
                break;
        }
    }

    private void doQuery() {

        // one speech cluster
        Criteria criteria = Criteria.where("romanization").is("ng");
        Query query = Query.query(criteria);
        KoreanSpeechCluster ksc = mongoTemplate.findOne(query, KoreanSpeechCluster.class);
        System.out.println("One type query: " + ksc + '\n');

        // query many clusters by type
         BasicQuery queryByType = new BasicQuery(" { type: 'Vowel' } ");
         List<KoreanSpeechCluster> vowels = mongoTemplate.find(queryByType, KoreanSpeechCluster.class);
         System.out.println("Query by type, clusters: ");
         vowels.forEach(System.out::println);
         System.out.println("\n");
         
        // all speech cluster
         List<KoreanSpeechCluster> kscs = mongoTemplate.findAll(KoreanSpeechCluster.class);
         System.out.println("Query for all speech clusters: ");
         kscs.forEach(System.out::println);
         System.out.println('\n');
         
    }

    private void doInsert() {

        // insert a sample of Korean speech clusters
        KoreanSpeechCluster constant1 = new KoreanSpeechCluster(
                "ㄱ", "g", SpeechType.CONSONANT);
        KoreanSpeechCluster constant2 = new KoreanSpeechCluster(
                "ㄴ", "n", SpeechType.CONSONANT);
        KoreanSpeechCluster constant3 = new KoreanSpeechCluster(
                "ㄷ", "d", SpeechType.CONSONANT);
        KoreanSpeechCluster endconstant1 = new KoreanSpeechCluster(
                "ㄱ", "k", SpeechType.END_CONSONANT);
        KoreanSpeechCluster endconstant2 = new KoreanSpeechCluster(
                "ㄷ", "t", SpeechType.END_CONSONANT);
        KoreanSpeechCluster vowel1 = new KoreanSpeechCluster(
                "ㅏ", "ah", SpeechType.VOWEL);
        KoreanSpeechCluster vowel2 = new KoreanSpeechCluster(
                "ㅓ", "uh", SpeechType.VOWEL);
        KoreanSpeechCluster vowel3 = new KoreanSpeechCluster(
                "ㅠ", "yoo", SpeechType.VOWEL);

        // insert one at a time
        mongoTemplate.insert(constant1);
        mongoTemplate.insert(constant2);
        mongoTemplate.insert(constant3);

        // insert many
        List<KoreanSpeechCluster> clusters = Arrays.asList(
                endconstant1,
                endconstant2,
                vowel1,
                vowel2,
                vowel3);
        mongoTemplate.insert(clusters, KoreanSpeechCluster.class);

    }

}
