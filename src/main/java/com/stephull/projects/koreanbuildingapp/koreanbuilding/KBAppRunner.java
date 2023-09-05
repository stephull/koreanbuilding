package com.stephull.projects.koreanbuildingapp.koreanbuilding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//import com.stephull.projects.koreanbuildingapp.service.KoreanPronunciationService;
import com.stephull.projects.koreanbuildingapp.service.KoreanBuildService;
import com.stephull.projects.koreanbuildingapp.service.KoreanSpeechClusterService;

@Component
public class KBAppRunner implements CommandLineRunner {

    @Autowired
    private final KoreanSpeechClusterService kscservice;

    @Autowired
    private final KoreanBuildService kbservice;

    //@Autowired
    //private final KoreanPronunciationService kpservice;

    public KBAppRunner(
        KoreanSpeechClusterService kscservice,
        KoreanBuildService kbservice
        //KoreanPronunciationService kpservice
    ) {
        this.kscservice = kscservice;
        this.kbservice = kbservice;
        //this.kpservice = kpservice;
    }

    @Override
    public void run(String... args) throws Exception {
        // add all documents into mongodb collection:
        kscservice.executeAllDocumentCreation();

        // add all Korean Builds via large csv file (made from Jupyter):
        kbservice.executeAllDocumentCreation();

        // for pronunciation rules
        //kpservice.executeAllDocumentCreation();
    }

}
