package com.stephull.projects.koreanbuildingapp.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KoreanUnicodeProvider {

    // 0x1100
    @Value("${korean.unicode.normal-jamo-range-start}")
    private int normalJamoRangeStart;

    // 0x11C2
    @Value("${korean.unicode.normal-jamo-range-end}")
    private int normalJamoRangeEnd;

    // 0x3131
    @Value("${korean.unicode.compatible-jamo-range-start}")
    private int compatibleJamoRangeStart;

    // 0x3163
    @Value("${korean.unicode.compatible-jamo-range-end}")
    private int compatibleJamoRangeEnd;

    // 0x1100
    @Value("${korean.unicode.normal-jamo-consonant-range-start}")
    private int normalJamoConsonantRangeStart;

    // 0x1112
    @Value("${korean.unicode.normal-jamo-consonant-range-end}")
    private int normalJamoConsonantRangeEnd;

    // 0x1161
    @Value("${korean.unicode.normal-jamo-vowel-range-start}")
    private int normalJamoVowelRangeStart;

    // 0x1175
    @Value("${korean.unicode.normal-jamo-vowel-range-end}")
    private int normalJamoVowelRangeEnd;

    // 0x11A8
    @Value("${korean.unicode.normal-jamo-end-consonant-range-start}")
    private int normalJamoEndConsonantRangeStart;

    // 0x11C2
    @Value("${korean.unicode.normal-jamo-end-consonant-range-end}")
    private int normalJamoEndConsonantRangeEnd;

    // 0xAC00
    @Value("${korean.unicode.syllable-character-range-start}")
    private int syllableCharacterRangeStart;

    // 0xD7A3
    @Value("${korean.unicode.syllable-character-range-end}")
    private int syllableCharacterRangeEnd;

    @Value("${korean.unicode.consonant-count}")
    private int consonantCount;

    @Value("${korean.unicode.vowel-count}")
    private int vowelCount;

    @Value("${korean.unicode.end-consonant-count}")
    private int endConsonantCount;

    /**
     * 
     * @return int
     */
    public int getNormalJamoRangeStart() {
        return this.normalJamoRangeStart;
    }

    /**
     * 
     * @return int
     */
    public int getNormalJamoRangeEnd() {
        return this.normalJamoRangeEnd;
    }

    /**
     * 
     * @return int
     */
    public int getCompatibleJamoRangeStart() {
        return this.compatibleJamoRangeStart;
    }

    /**
     * 
     * @return int
     */
    public int getCompatibleJamoRangeEnd() {
        return this.compatibleJamoRangeEnd;
    }

    /**
     * 
     * @return int
     */
    public int getNormalJamoConsonantRangeStart() {
        return this.normalJamoConsonantRangeStart;
    }

    /**
     * 
     * @return int
     */
    public int getNormalJamoConsonantRangeEnd() {
        return this.normalJamoConsonantRangeEnd;
    }

    /**
     * 
     * @return int
     */
    public int getNormalJamoVowelRangeStart() {
        return this.normalJamoVowelRangeStart;
    }

    /**
     * 
     * @return int
     */
    public int getNormalJamoVowelRangeEnd() {
        return this.normalJamoVowelRangeEnd;
    }

    /**
     * 
     * @return int
     */
    public int getNormalJamoEndConsonantRangeStart() {
        return this.normalJamoEndConsonantRangeStart;
    }

    /**
     * 
     * @return int
     */
    public int getNormalJamoEndConsonantRangeEnd() {
        return this.normalJamoEndConsonantRangeEnd;
    }

    /**
     * 
     * @return int
     */
    public int getSyllableCharacterRangeStart() {
        return this.syllableCharacterRangeStart;
    }

    /**
     * 
     * @return int
     */
    public int getSyllableCharacterRangeEnd() {
        return this.syllableCharacterRangeEnd;
    }

    /**
     * 
     * @return int
     */
    public int getConsonantCount() {
        return this.consonantCount;
    }

    /**
     * 
     * @return int
     */
    public int getVowelCount() {
        return this.vowelCount;
    }

    /**
     * 
     * @return int
     */
    public int getEndConsonantCount() {
        return this.endConsonantCount;
    }
}
