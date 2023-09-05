package com.stephull.projects.koreanbuildingapp.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
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
import com.stephull.projects.koreanbuildingapp.model.CustomID;
import com.stephull.projects.koreanbuildingapp.model.KoreanBuild;
import com.stephull.projects.koreanbuildingapp.model.KoreanSpeechCluster;
import com.stephull.projects.koreanbuildingapp.repository.KoreanBuildRepository;

@Service
public class KoreanBuildService implements FileProviderService<KoreanBuild>, DataProviderService<KoreanBuild> {
    
    private String mongoClientConnectionString = "";
    private String mongoClientDatabase = "KoreanBuildingApp";

    @Autowired 
    private final KoreanBuildRepository kbrepo;
    
    @Autowired
    private final KoreanLetterConversion klc;

    @Autowired
    private final MongoTemplate mongoTemplate;

    public KoreanBuildService(
        KoreanBuildRepository kbrepo,
        KoreanLetterConversion klc,
        MongoTemplate mongoTemplate
    ) {
        this.kbrepo = kbrepo;
        this.klc = klc;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<KoreanBuild> processCSVFile(File file) {
        try {            
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
        
            CSVParser csvParser = new CSVParser(bufferedReader, CSVFormat.DEFAULT);
            // withHeader() is deprecated, find other ways to get headers if needed!!

            List<KoreanBuild> builds = new ArrayList<KoreanBuild>();

            for (CSVRecord record : csvParser) {
                KoreanBuild newKoreanBuild = new KoreanBuild();
                //newKoreanBuild.set
            }

            csvParser.close();

            return builds;
            
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    /**
     * 
     * @param build
     * @return List<KoreanSpeechCluster>
     */
    private List<KoreanSpeechCluster> setAssemblyOfBuildIntoDocument(KoreanBuild build) {
        List<KoreanSpeechCluster> newAssembly = new ArrayList<KoreanSpeechCluster>();
        
        //

        return newAssembly;
    }

    /**
     * 
     * @return boolean
     */
    @Override
    public boolean isCollectionEmpty() {
        return kbrepo.count() == 0;
    }

    /**
     * 
     * @param database
     * @param build
     */
    @Override
    public void addObjectToCollection(MongoDatabase database, KoreanBuild build) {
        String association = KoreanBuild.class.getName();
        MongoCollection<Document> sequenceCollection = database.getCollection("sequences");

        Document sequenceDoc = sequenceCollection.findOneAndUpdate(
            new Document("id", association),
            new Document("$inc", new Document("seq", 1L)),
            new FindOneAndUpdateOptions().upsert(true).returnDocument(ReturnDocument.AFTER)
        );

        long nextSeqValue = sequenceDoc.getLong("seq");

        CustomID<KoreanBuild> customId = new CustomID<KoreanBuild>(
            String.valueOf(nextSeqValue-1), association
        );
        build.setKbId(customId);

        mongoTemplate.save(build);
    }

    /**
     * 
     */
    @Override
    public void executeAllDocumentCreation() {
        try {
            MongoClient mongoClient = MongoClients.create(mongoClientConnectionString);
            MongoDatabase database = mongoClient.getDatabase(mongoClientDatabase);

            if (!this.isCollectionEmpty()) return;

            String csvFilePath = "";
            File csvFile = new File(csvFilePath);
            
            List<KoreanBuild> koreanBuilds = this.processCSVFile(csvFile);
            if (koreanBuilds == null) return;

            for (KoreanBuild build : koreanBuilds) {
                List<KoreanSpeechCluster> assembly = this.setAssemblyOfBuildIntoDocument(build);
                build.setAssembly(assembly);

                this.addObjectToCollection(database, build);
            }

            mongoClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
