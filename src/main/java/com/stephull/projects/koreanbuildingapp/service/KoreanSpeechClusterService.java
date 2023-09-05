package com.stephull.projects.koreanbuildingapp.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.stephull.projects.koreanbuildingapp.data.KoreanSpeechClusterData;
import com.stephull.projects.koreanbuildingapp.model.CustomID;
import com.stephull.projects.koreanbuildingapp.model.KoreanSpeechCluster;
import com.stephull.projects.koreanbuildingapp.model.SpeechType;
import com.stephull.projects.koreanbuildingapp.repository.KoreanSpeechClusterRepository;

@Service
public class KoreanSpeechClusterService implements DataProviderService<KoreanSpeechCluster> {
    
    private String mongoClientConnectionString = "";
    private String mongoClientDatabase = "KoreanBuildingApp";

    @Autowired
    private final KoreanSpeechClusterRepository kscrepo;
    
    @Autowired
    private final KoreanSpeechClusterData kscdata;
    
    @Autowired
    private final KoreanLetterConversion klc;

    @Autowired
    private final MongoTemplate mongoTemplate;

    public KoreanSpeechClusterService(
        KoreanSpeechClusterRepository kscrepo,
        KoreanSpeechClusterData kscdata,
        KoreanLetterConversion klc,
        MongoTemplate mongoTemplate
    ) {
        this.kscrepo = kscrepo;
        this.kscdata = kscdata;
        this.klc = klc;
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * 
     * @param currentClusterList
     * @return List<KoreanSpeechCluster>
     */
    private List<KoreanSpeechCluster> updateClusterListsWithCompoundPairs(List<KoreanSpeechCluster> currentClusterList) {
        for (KoreanSpeechCluster cluster: currentClusterList) {
            // for each cluster, update the cluster's compounds/relationship properties
            
        }
        return currentClusterList;
    }

    /**
     * 
     * @param table
     * @return List<KoreanSpeechCluster>
     */
    private List<KoreanSpeechCluster> insertClusterInformation(String[][] table) {
        List<KoreanSpeechCluster> res = new ArrayList<KoreanSpeechCluster>();
        for (String[] e : table) {
            String letter = e[0], positioned = e[1], sound = e[2], type = e[3];            
            KoreanSpeechCluster newCluster = new KoreanSpeechCluster(letter, positioned, sound, type);
            res.add(newCluster);
        }
        return res;
    }

    /**
     * 
     * @return boolean
     */
    @Override
    public boolean isCollectionEmpty() {
        return kscrepo.count() == 0;
    }

    /**
     * 
     * @param database
     * @param cluster
     */
    @Override
    public void addObjectToCollection(MongoDatabase database, KoreanSpeechCluster cluster) {
        String association = KoreanSpeechCluster.class.getName();
        MongoCollection<Document> sequenceCollection = database.getCollection("sequences");
        
        Document sequenceDoc = sequenceCollection.findOneAndUpdate(
            new Document("id", association),
            new Document("$inc", new Document("seq", 1L)),
            new FindOneAndUpdateOptions().upsert(true).returnDocument(ReturnDocument.AFTER)
        );

        long nextSeqValue = sequenceDoc.getLong("seq");

        CustomID<KoreanSpeechCluster> customId = new CustomID<KoreanSpeechCluster>(
            String.valueOf(nextSeqValue-1), association
        );
        cluster.setKscId(customId);

        mongoTemplate.save(cluster);
    }

    /**
     * 
     */
    @Override
    public void executeAllDocumentCreation() {
        try {
            // connect to MongoDB database
            MongoClient mongoClient = MongoClients.create(mongoClientConnectionString);
            MongoDatabase database = mongoClient.getDatabase(mongoClientDatabase);

            // check for empty conditions; do not proceed if documents exist in collection
            if (!this.isCollectionEmpty()) return;

            // gather data from our source directory
            String[][] fcData = kscdata.getFirstConsonants();
            String[][] vowelData = kscdata.getVowels();
            String[][] ecData = kscdata.getEndingConsonants();

            // fetch all the contents and insert them into out KoreanSpeechCluster class
            List<KoreanSpeechCluster> consonants = this.insertClusterInformation(fcData);
            List<KoreanSpeechCluster> vowels = this.insertClusterInformation(vowelData);
            List<KoreanSpeechCluster> endingConsonants = this.insertClusterInformation(ecData);

            // using our now-existing clusters, update them with their corresponding compound pairs
            consonants = this.updateClusterListsWithCompoundPairs(consonants);
            vowels = this.updateClusterListsWithCompoundPairs(vowels);
            endingConsonants = this.updateClusterListsWithCompoundPairs(endingConsonants);

            // starting with our consonant list, append all lists together
            List<KoreanSpeechCluster> allSoundClusters = consonants;
            allSoundClusters.addAll(vowels);
            allSoundClusters.addAll(endingConsonants);

            // for each cluster given, add to the MongoDB collection
            for (KoreanSpeechCluster cluster : allSoundClusters) {
                this.addObjectToCollection(database, cluster);
            }

            // and finally, close the connection between MongoDB client and this program
            mongoClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
