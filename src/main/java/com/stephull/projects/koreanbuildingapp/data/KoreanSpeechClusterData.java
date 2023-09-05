package com.stephull.projects.koreanbuildingapp.data;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stephull.projects.koreanbuildingapp.model.SpeechType;

@Component
public class KoreanSpeechClusterData {

    @Autowired
    private final KoreanUnicodeProvider kup;

    public KoreanSpeechClusterData(
        KoreanUnicodeProvider kup
    ) {
        this.kup = kup;
    }

    private final int[][] consonantCompoundComparisonTable = {
        { 0x1100, 0x1100, 0x1101 },
        { 0x1103, 0x1103, 0x1104 },
        { 0x1107, 0x1107, 0x1108 },
        { 0x1109, 0x1109, 0x110A },
        { 0x110C, 0x110C, 0x110D }
    };

    private final int[][] vowelCompoundComparisonTable = {
        { 0x1169, 0x1161, 0x116A },
        { 0x1169, 0x1162, 0x116B },
        { 0x1169, 0x1175, 0x116C },
        { 0x116E, 0x1165, 0x116F },
        { 0x116E, 0x1166, 0x1170 },
        { 0x116E, 0x1175, 0x1171 },
        { 0x1173, 0x1175, 0x1174 }
    };

    private final int[][] endingConsonantCompoundComparisonTable = {
        { 0x11A8, 0x11A8, 0x11A9 },
        { 0x11A8, 0x11BA, 0x11AA },
        { 0x11AB, 0x11BD, 0x11AC },
        { 0x11AB, 0x11C2, 0x11AD },
        { 0x11AF, 0x11A8, 0x11B0 },
        { 0x11AF, 0x11B7, 0x11B1 },
        { 0x11AF, 0x11B8, 0x11B2 },
        { 0x11AF, 0x11BA, 0x11B3 },
        { 0x11AF, 0x11C0, 0x11B4 },
        { 0x11AF, 0x11C1, 0x11B5 },
        { 0x11AF, 0x11C2, 0x11B6 },
        { 0x11B8, 0x11BA, 0x11B9 },
        { 0x11BA, 0x11BA, 0x11BB }
    };

    private final String[][] firstConsonantSoundTable = {
        { "ㄱ", "\u1100", "g", SpeechType.CONSONANT },
        { "ㄲ", "\u1101", "gg", SpeechType.CONSONANT },
        { "ㄴ", "\u1102", "n", SpeechType.CONSONANT },
        { "ㄷ", "\u1103", "d", SpeechType.CONSONANT },
        { "ㄸ", "\u1104", "dd", SpeechType.CONSONANT },
        { "ㄹ", "\u1105", "r", SpeechType.CONSONANT },
        { "ㅁ", "\u1106", "m", SpeechType.CONSONANT },
        { "ㅂ", "\u1107", "b", SpeechType.CONSONANT },
        { "ㅃ", "\u1108", "pp", SpeechType.CONSONANT },
        { "ㅅ", "\u1109", "s", SpeechType.CONSONANT },
        { "ㅆ", "\u110A", "ss", SpeechType.CONSONANT },
        { "ㅇ", "\u110B", "", SpeechType.CONSONANT },
        { "ㅈ", "\u110C", "j", SpeechType.CONSONANT },
        { "ㅉ", "\u110D", "jj", SpeechType.CONSONANT },
        { "ㅊ", "\u110E", "ch", SpeechType.CONSONANT },
        { "ㅋ", "\u110F", "k", SpeechType.CONSONANT },
        { "ㅌ", "\u1110", "t", SpeechType.CONSONANT },
        { "ㅍ", "\u1111", "p", SpeechType.CONSONANT },
        { "ㅎ", "\u1112", "h", SpeechType.CONSONANT }
    };

    private final String[][] vowelSoundTable = {
        { "ㅏ", "\u1161", "a", SpeechType.VOWEL },
        { "ㅐ", "\u1162", "ae", SpeechType.VOWEL },
        { "ㅑ", "\u1163", "ya", SpeechType.VOWEL },
        { "ㅒ", "\u1164", "yae", SpeechType.VOWEL },
        { "ㅓ", "\u1165", "eo", SpeechType.VOWEL },
        { "ㅔ", "\u1166", "e", SpeechType.VOWEL },
        { "ㅕ", "\u1167", "yeo", SpeechType.VOWEL },
        { "ㅖ", "\u1168", "ye", SpeechType.VOWEL },
        { "ㅗ", "\u1169", "o", SpeechType.VOWEL },
        { "ㅘ", "\u116A", "wa", SpeechType.VOWEL },
        { "ㅙ", "\u116B", "wae", SpeechType.VOWEL },
        { "ㅚ", "\u116C", "oe", SpeechType.VOWEL },
        { "ㅛ", "\u116D", "yo", SpeechType.VOWEL },
        { "ㅜ", "\u116E", "u", SpeechType.VOWEL },
        { "ㅝ", "\u116F", "weo", SpeechType.VOWEL },
        { "ㅞ", "\u1170", "we", SpeechType.VOWEL },
        { "ㅟ", "\u1171", "wi", SpeechType.VOWEL },
        { "ㅠ", "\u1172", "yu", SpeechType.VOWEL },
        { "ㅡ", "\u1173", "eu", SpeechType.VOWEL },
        { "ㅢ", "\u1174", "yi", SpeechType.VOWEL },
        { "ㅣ", "\u1175", "i", SpeechType.VOWEL }
    };

    private final String[][] endingConsonantSoundTable = {
        { "ㄱ", "\u11A8", "g", SpeechType.END_CONSONANT },
        { "ㄲ", "\u11A9", "gg", SpeechType.END_CONSONANT },
        { "ㄳ", "\u11AA", "gs", SpeechType.END_CONSONANT },
        { "ㄴ", "\u11AB", "n", SpeechType.END_CONSONANT },
        { "ㄵ", "\u11AC", "nj", SpeechType.END_CONSONANT },
        { "ㄶ", "\u11AD", "nh", SpeechType.END_CONSONANT },
        { "ㄷ", "\u11AE", "d", SpeechType.END_CONSONANT },
        { "ㄹ", "\u11AF", "l", SpeechType.END_CONSONANT },
        { "ㄺ", "\u11B0", "lk", SpeechType.END_CONSONANT },
        { "ㄻ", "\u11B1", "lm", SpeechType.END_CONSONANT },
        { "ㄼ", "\u11B2", "lp", SpeechType.END_CONSONANT },
        { "ㄽ", "\u11B3", "ls", SpeechType.END_CONSONANT },
        { "ㄾ", "\u11B4", "lt", SpeechType.END_CONSONANT },
        { "ㄿ", "\u11B5", "lp", SpeechType.END_CONSONANT },
        { "ㅀ", "\u11B6", "lh", SpeechType.END_CONSONANT },
        { "ㅁ", "\u11B7", "m", SpeechType.END_CONSONANT },
        { "ㅂ", "\u11B8", "b", SpeechType.END_CONSONANT },
        { "ㅄ", "\u11B9", "bs", SpeechType.END_CONSONANT },
        { "ㅅ", "\u11BA", "s", SpeechType.END_CONSONANT },
        { "ㅆ", "\u11BB", "ss", SpeechType.END_CONSONANT },
        { "ㅇ", "\u11BC", "ng", SpeechType.END_CONSONANT },
        { "ㅈ", "\u11BD", "j", SpeechType.END_CONSONANT },
        { "ㅊ", "\u11BE", "ch", SpeechType.END_CONSONANT },
        { "ㅋ", "\u11BF", "k", SpeechType.END_CONSONANT },
        { "ㅌ", "\u11C0", "t", SpeechType.END_CONSONANT },
        { "ㅍ", "\u11C1", "p", SpeechType.END_CONSONANT },
        { "ㅎ", "\u11C2", "h", SpeechType.END_CONSONANT }
    };

    /**
     * 
     * @return int[][]
     */
    public int[][] getConsonantCompoundComparisonTable() {
        return this.consonantCompoundComparisonTable;
    }

    /**
     * 
     * @return int[][]
     */
    public int[][] getVowelCompoundComparisonTable() {
        return this.vowelCompoundComparisonTable;
    }

    /**
     * 
     * @return int[][]
     */
    public int[][] getEndingConsonantCompoundComparisonTable() {
        return this.endingConsonantCompoundComparisonTable;
    }

    /**
     * 
     * @return String[][]
     */
    public String[][] getFirstConsonants() {
        return this.firstConsonantSoundTable;
    }

    /**
     * 
     * @return String[][]
     */
    public String[][] getVowels() {
        return this.vowelSoundTable;
    }

    /**
     * 
     * @return String[][]
     */
    public String[][] getEndingConsonants() {
        return this.endingConsonantSoundTable;
    }

    /**
     * 
     * @param positioned
     * @return Map<String, String>
     */
    public Map<String, String> fetchCompoundConsonantOrVowel(String positioned, String type) {
        int positionedCode = (int) positioned.charAt(0);
        if (
            positionedCode < kup.getNormalJamoRangeStart() 
            || positionedCode > kup.getNormalJamoRangeEnd()
        ) {
            return null;
        }

        int[][] table;
        switch(type) {
            case SpeechType.CONSONANT:
                table = this.getConsonantCompoundComparisonTable();
                break;
            case SpeechType.VOWEL:
                table = this.getVowelCompoundComparisonTable();
                break;
            case SpeechType.END_CONSONANT:
                table = this.getEndingConsonantCompoundComparisonTable();
                break;
            default:
                return null;
        }

        Map<String, String> res = new LinkedHashMap<String, String>();

        // fetch the necessary characters from the lookup table
        // map entry should have the additional letter as key,
        // and the compound character as the value
        for (int[] row : table) {
            if (positionedCode == row[0]) {
                String a = Character.toString((char) row[1]);
                String b = Character.toString((char) row[2]);
                res.put(a, b);
            }
        }

        return res;
    }
}
