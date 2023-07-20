package com.stephull.projects.koreanbuildingapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stephull.projects.koreanbuildingapp.repository.KBRepository;

@Service
public class KBService {
    
    @Autowired
    private KBRepository kbrepo;

    @Autowired
    private KoreanLetterConversion klc;

    public KBService(
        KBRepository kbrepo,
        KoreanLetterConversion klc
    ) {
        this.kbrepo = kbrepo;
        this.klc = klc;
    }

}
