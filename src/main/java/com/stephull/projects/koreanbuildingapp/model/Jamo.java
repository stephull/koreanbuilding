package com.stephull.projects.koreanbuildingapp.model;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Jamo {

    // all consonants
    public static final String G = "ㄱ";
    public static final String N = "ㄴ";
    public static final String D = "ㄷ";
    public static final String L = "ㄹ";
    public static final String M = "ㅁ";
    public static final String B = "ㅂ";
    public static final String S = "ㅅ";
    public static final String W = "ㅇ";
    public static final String J = "ㅈ";
    public static final String CH = "ㅊ";
    public static final String K = "ㅋ";
    public static final String T = "ㅌ";
    public static final String P = "ㅍ";
    public static final String H = "ㅎ";
    public static final String KK = "ㄲ";
    public static final String TT = "ㄸ";
    public static final String PP = "ㅃ";
    public static final String SS = "ㅆ";
    public static final String JJ = "ㅉ";

    // all vowels
    public static final String AH = "ㅏ";
    public static final String YAH = "ㅑ";
    public static final String EO = "ㅓ";
    public static final String YEO = "ㅕ";
    public static final String OH = "ㅗ";
    public static final String YOH = "ㅛ";
    public static final String OO = "ㅜ";
    public static final String YOO = "ㅠ";
    public static final String EU = "ㅡ";
    public static final String EE = "ㅣ";
    public static final String AE = "ㅐ";
    public static final String YAE = "ㅒ";
    public static final String EH = "ㅔ";
    public static final String YEH = "ㅖ";

    // bonus vowels
    public static final String WA = "ㅘ";
    public static final String WAE = "ㅙ";
    public static final String OE = "ㅚ";
    public static final String WO = "ㅝ";
    public static final String WEH = "ㅞ";
    public static final String WI = "ㅟ";
    public static final String UI = "ㅢ";

}

class JamoIterator {
    public void iterate() throws IllegalAccessException {
        Class<Jamo> jc = Jamo.class;
        for (Field f: jc.getDeclaredFields()) {
            int mods = f.getModifiers();
            if (
                f.getType() == String.class 
                && Modifier.isPublic(mods)
                && Modifier.isStatic(mods)
            ) {
                String val = (String)f.get(null);
                System.out.println(val);
            }
        }
    }
}

class JamoIteratorDemo {
    public static void main(String[] args) {
        JamoIterator ji = new JamoIterator();
        try {
            ji.iterate();
        } catch (IllegalAccessException e) {
            System.out.println(e);
        }
    }
}