package com.stephull.projects.koreanbuildingapp.service;

import org.springframework.stereotype.Service;

@Service
public class ChineseOriginService {
    
    private static final int CJK_UNIFIED_IDEOGRAPH_RANGE_START = 0x4E00;
    private static final int CJK_UNIFIED_IDEOGRAPH_RANGE_END = 0x9FFF;

    // do repo later

    public boolean checkForChineseUnicode(String entry) {
        int code = (int) entry.charAt(0);
        return (
            code >= CJK_UNIFIED_IDEOGRAPH_RANGE_START 
            && code <= CJK_UNIFIED_IDEOGRAPH_RANGE_END
        );
    }

}
