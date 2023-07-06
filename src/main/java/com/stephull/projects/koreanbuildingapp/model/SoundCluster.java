package com.stephull.projects.koreanbuildingapp.model;

import java.util.Map;
import java.util.HashMap;

public class SoundCluster {
    
    private Map<String, String> vowelSoundClusters;
    private Map<String, String> ordinaryConsonantSoundClusters;
    private Map<String, String> endingConsonantSoundClusters;

    public SoundCluster() {
        vowelSoundClusters = new HashMap<String, String>();
        ordinaryConsonantSoundClusters = new HashMap<String, String>();
        endingConsonantSoundClusters = new HashMap<String, String>();
    }

    // for vowels (all)

    public void addVowelMapping(String character, String sound) {
        vowelSoundClusters.put(character, sound);
    }

    public String getVowelSoundValue(String character) {
        return vowelSoundClusters.get(character);
    }

    public boolean containsVowelCharacter(String character) {
        return vowelSoundClusters.containsKey(character);
    }

    public void removeVowelMapping(String character) {
        vowelSoundClusters.remove(character);
    }

    // for ordinary consonants

    public void addOrdinaryConsonantMapping(String character, String sound) {
        ordinaryConsonantSoundClusters.put(character, sound);
    }

    public String getOrdinaryConsonantSoundValue(String character) {
        return ordinaryConsonantSoundClusters.get(character);
    }

    public boolean containsOrdinaryConsonantCharacter(String character) {
        return ordinaryConsonantSoundClusters.containsKey(character);
    }

    public void removeOrdinaryConsonantMapping(String character) {
        ordinaryConsonantSoundClusters.remove(character);
    }

    // and for ending consonants

    public void addEndingConsonantMapping(String character, String sound) {
        endingConsonantSoundClusters.put(character, sound);
    }

    public String getEndingConsonantSoundValue(String character) {
        return endingConsonantSoundClusters.get(character);
    }

    public boolean containsEndingConsonantCharacter(String character) {
        return endingConsonantSoundClusters.containsKey(character);
    }

    public void removeEndingConsonantMapping(String character) {
        endingConsonantSoundClusters.remove(character);
    }

}

class SoundClusterDemo {
    public static void main(String[] args) {
        SoundCluster sc = new SoundCluster();

        sc.addVowelMapping(Jamo.AH, "ah");
        sc.addVowelMapping(Jamo.EE, "ee");
        
        sc.addOrdinaryConsonantMapping(Jamo.B, "b");
        sc.addOrdinaryConsonantMapping(Jamo.W, "w");

        sc.addEndingConsonantMapping(Jamo.B, "p");
        sc.addEndingConsonantMapping(Jamo.W, "ng");

        System.out.println("Vowels:");
        System.out.println(sc.getVowelSoundValue(Jamo.AH));
        System.out.println(sc.getVowelSoundValue(Jamo.EE));

        System.out.println("\nOrdinary consonants:");
        System.out.println(sc.getOrdinaryConsonantSoundValue(Jamo.B));

        System.out.println("\nEnding consonants:");
        System.out.println(sc.getEndingConsonantSoundValue(Jamo.W));
    }
}