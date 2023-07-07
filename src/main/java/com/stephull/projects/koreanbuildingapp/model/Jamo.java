package com.stephull.projects.koreanbuildingapp.model;

import java.util.ArrayList;
import java.util.List;

public class Jamo<E> {

    private String value;

    protected Jamo(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String newValue) {
        this.value = newValue;
    }

}

class Vowel extends Jamo<Vowel> {
    public Vowel(String value) {
        super(value);
    }

    public static final Vowel VOWEL_AH = new Vowel("ㅏ");
    public static final Vowel VOWEL_YAH = new Vowel("ㅑ");
    public static final Vowel VOWEL_EO = new Vowel("ㅓ");
    public static final Vowel VOWEL_YEO = new Vowel("ㅕ");
    public static final Vowel VOWEL_OH = new Vowel("ㅗ");
    public static final Vowel VOWEL_YOH = new Vowel("ㅛ");
    public static final Vowel VOWEL_OO = new Vowel("ㅜ");
    public static final Vowel VOWEL_YOO = new Vowel("ㅠ");
    public static final Vowel VOWEL_EU = new Vowel("ㅡ");
    public static final Vowel VOWEL_EE = new Vowel("ㅣ");
    public static final Vowel VOWEL_AE = new Vowel("ㅐ");
    public static final Vowel VOWEL_YAE = new Vowel("ㅒ");
    public static final Vowel VOWEL_EH = new Vowel("ㅔ");
    public static final Vowel VOWEL_YEH = new Vowel("ㅖ");

    public static final Vowel VOWEL_EXTRA_WA = new Vowel("ㅘ");
    public static final Vowel VOWEL_EXTRA_WAE = new Vowel("ㅙ");
    public static final Vowel VOWEL_EXTRA_OE = new Vowel("ㅚ");
    public static final Vowel VOWEL_EXTRA_WO = new Vowel("ㅝ");
    public static final Vowel VOWEL_EXTRA_WEH = new Vowel("ㅞ");
    public static final Vowel VOWEL_EXTRA_WI = new Vowel("ㅟ");
    public static final Vowel VOWEL_EXTRA_UI = new Vowel("ㅢ");

    // array
    public static List<Vowel> getAllVowels() {
        List<Vowel> l = new ArrayList<Vowel>();

        l.add(VOWEL_AH);
        l.add(VOWEL_YAH);
        l.add(VOWEL_EO);
        l.add(VOWEL_YEO);
        l.add(VOWEL_OH);
        l.add(VOWEL_YOH);
        l.add(VOWEL_OO);
        l.add(VOWEL_YOO);
        l.add(VOWEL_EU);
        l.add(VOWEL_EE);
        l.add(VOWEL_AE);
        l.add(VOWEL_YAE);
        l.add(VOWEL_EH);
        l.add(VOWEL_YEH);
        l.add(VOWEL_EXTRA_WA);
        l.add(VOWEL_EXTRA_WAE);
        l.add(VOWEL_EXTRA_OE);
        l.add(VOWEL_EXTRA_WO);
        l.add(VOWEL_EXTRA_WEH);
        l.add(VOWEL_EXTRA_WI);
        l.add(VOWEL_EXTRA_UI);

        return l;
    }
}

class Const extends Jamo<Const> {
    public Const(String value) {
        super(value);
    }

    public static final Const CONST_G = new Const("ㄱ");
    public static final Const CONST_N = new Const("ㄴ");
    public static final Const CONST_D = new Const("ㄷ");
    public static final Const CONST_L = new Const("ㄹ");
    public static final Const CONST_M = new Const("ㅁ");
    public static final Const CONST_B = new Const("ㅂ");
    public static final Const CONST_S = new Const("ㅅ");
    public static final Const CONST_W = new Const("ㅇ");
    public static final Const CONST_J = new Const("ㅈ");
    public static final Const CONST_CH = new Const("ㅊ");
    public static final Const CONST_K = new Const("ㅋ");
    public static final Const CONST_T = new Const("ㅌ");
    public static final Const CONST_P = new Const("ㅍ");
    public static final Const CONST_H = new Const("ㅎ");
    public static final Const CONST_KK = new Const("ㄲ");
    public static final Const CONST_TT = new Const("ㄸ");
    public static final Const CONST_PP = new Const("ㅃ");
    public static final Const CONST_SS = new Const("ㅆ");
    public static final Const CONST_JJ = new Const("ㅉ");

    // array
    public static List<Const> getAllConsonants() {
        List<Const> l = new ArrayList<Const>();

        l.add(CONST_G);
        l.add(CONST_N);
        l.add(CONST_D);
        l.add(CONST_L);
        l.add(CONST_M);
        l.add(CONST_B);
        l.add(CONST_S);
        l.add(CONST_W);
        l.add(CONST_J);
        l.add(CONST_CH);
        l.add(CONST_K);
        l.add(CONST_T);
        l.add(CONST_P);
        l.add(CONST_H);
        l.add(CONST_KK);
        l.add(CONST_TT);
        l.add(CONST_PP);
        l.add(CONST_SS);
        l.add(CONST_JJ);

        return l;
    }
}

class EndConst extends Jamo<EndConst> {
    public EndConst(String value) {
        super(value);
    }

    public static final EndConst CONST_END_G = new EndConst("ㄱ");
    public static final EndConst CONST_END_KK = new EndConst("ㄲ");
    public static final EndConst CONST_END_GS = new EndConst("ㄳ");
    public static final EndConst CONST_END_N = new EndConst("ㄴ");
    public static final EndConst CONST_END_NJ = new EndConst("ㄵ");
    public static final EndConst CONST_END_NH = new EndConst("ㄶ");
    public static final EndConst CONST_END_D = new EndConst("ㄷ");
    public static final EndConst CONST_END_L = new EndConst("ㄹ");
    public static final EndConst CONST_END_LG = new EndConst("ㄺ");
    public static final EndConst CONST_END_LM = new EndConst("ㄻ");
    public static final EndConst CONST_END_LB = new EndConst("ㄼ");
    public static final EndConst CONST_END_LS = new EndConst("ㄽ");
    public static final EndConst CONST_END_LT = new EndConst("ㄾ");
    public static final EndConst CONST_END_LP = new EndConst("ㄿ");
    public static final EndConst CONST_END_LH = new EndConst("ㅀ");
    public static final EndConst CONST_END_M = new EndConst("ㅁ");
    public static final EndConst CONST_END_B = new EndConst("ㅂ");
    public static final EndConst CONST_END_BS = new EndConst("ㅄ");
    public static final EndConst CONST_END_S = new EndConst("ㅅ");
    public static final EndConst CONST_END_SS = new EndConst("ㅆ");
    public static final EndConst CONST_END_NG = new EndConst("ㅇ");
    public static final EndConst CONST_END_J = new EndConst("ㅈ");
    public static final EndConst CONST_END_CH = new EndConst("ㅊ");
    public static final EndConst CONST_END_K = new EndConst("ㅋ");
    public static final EndConst CONST_END_T = new EndConst("ㅌ");
    public static final EndConst CONST_END_P = new EndConst("ㅍ");
    public static final EndConst CONST_END_H = new EndConst("ㅎ");
    
    // array.
    public static List<EndConst> getAllEndingConsonants() {
        List<EndConst> l = new ArrayList<EndConst>();

        l.add(CONST_END_G);
        l.add(CONST_END_KK);
        l.add(CONST_END_GS);
        l.add(CONST_END_N);
        l.add(CONST_END_NJ);
        l.add(CONST_END_NH);
        l.add(CONST_END_D);
        l.add(CONST_END_L);
        l.add(CONST_END_LG);
        l.add(CONST_END_LM);
        l.add(CONST_END_LB);
        l.add(CONST_END_LS);
        l.add(CONST_END_LT);
        l.add(CONST_END_LP);
        l.add(CONST_END_LH);
        l.add(CONST_END_M);
        l.add(CONST_END_B);
        l.add(CONST_END_BS);
        l.add(CONST_END_S);
        l.add(CONST_END_SS);
        l.add(CONST_END_NG);
        l.add(CONST_END_J);
        l.add(CONST_END_CH);
        l.add(CONST_END_K);
        l.add(CONST_END_T);
        l.add(CONST_END_P);
        l.add(CONST_END_H);

        return l;
    }
    

}