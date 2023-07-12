package com.stephull.projects.koreanbuildingapp.service;

import com.stephull.projects.koreanbuildingapp.model.KoreanSpeechCluster;
import com.stephull.projects.koreanbuildingapp.model.SpeechType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class KoreanLetterConversion {
    
    private static final int INIT_CONST_RANGE_START = 0x1100;
    private static final int INIT_CONST_RANGE_END = 0x1112;
    private static final int VOWEL_RANGE_START = 0x1161;
    private static final int VOWEL_RANGE_END = 0x1175;
    private static final int FINAL_CONST_RANGE_START = 0x11A8;
    private static final int FINAL_CONST_RANGE_END = 0x11C2;
    private static final int CHARACTER_RANGE_START = 0xAC00;
    private static final int CHARACTER_RANGE_END = 0xD7A3;
    private static final int CONSONANT_INDEX = 28;
    private static final int VOWEL_INDEX = 21;

	/**
	 * Input of type String redirected to similar method that takes in char
	 * @param character
	 * @return List<KoreanSpeechCluster>
	 */
	public List<KoreanSpeechCluster> convertCharacterToClusterArray(String character) {
		if (character.length() < 1 || character.length() > 2) {
			return new ArrayList<KoreanSpeechCluster>();
		}
		return this.convertCharacterToClusterArray(character.charAt(0));
	}

	/**
	 * Converts Korean character to sequential letter array, then converts each
	 *  sound in letter array into speech clusters, with information on unicode and speech type
	 * @param character
	 * @return List<KoreanSpeechCluster>
	 */
	public List<KoreanSpeechCluster> convertCharacterToClusterArray(char character) {
		List<KoreanSpeechCluster> clusters = new ArrayList<KoreanSpeechCluster>();

		List<String> sounds = this.convertCharacterToLetterArray(character);
		for (String s : sounds) {
			int soundCode = (int) s.charAt(0);

			// ... for romanization of sound
			// (maybe fetch from database or speech-synthesis API ?!?)
			//String roman = kbrepo.findSoundRomanizationByLetter(s);

			SpeechType st;
			if (this.withinRange(soundCode, INIT_CONST_RANGE_START, INIT_CONST_RANGE_END)) {
				st = SpeechType.CONSONANT;
			} else if (this.withinRange(soundCode, VOWEL_RANGE_START, VOWEL_RANGE_END)) {
				st = SpeechType.VOWEL;
			} else if (this.withinRange(soundCode, FINAL_CONST_RANGE_START, FINAL_CONST_RANGE_END)) {
				st = SpeechType.END_CONSONANT;
			} else {
				st = null;
			}

			KoreanSpeechCluster k = new KoreanSpeechCluster(s, "test", st);
			clusters.add(k);
		}

		return clusters;
	}

	/**
	 * Input of type String redirected to similar method that takes in char
	 * @param character
	 * @return List<String>
	 */
	public List<String> convertCharacterToLetterArray(String character) {
		if (character.length() < 1 || character.length() > 2) {
			return new ArrayList<String>();
		}
		return this.convertCharacterToLetterArray(character.charAt(0));
	}

	/**
	 * Converts Korean character into an array of sequential ordered sounds
	 * @param character
	 * @return List<String> 
	 */
    public List<String> convertCharacterToLetterArray(char character) {
        List<String> jamoCharacters = new ArrayList<String>();

        int charCode = (int) character;
        if (charCode < CHARACTER_RANGE_START || charCode > CHARACTER_RANGE_END) {
            System.out.println("Invalid input (need proper Korean character). Try again!");
			return jamoCharacters; 
        }        
        
        int syllableIndex = charCode - CHARACTER_RANGE_START;
        int initialIndex = syllableIndex / (CONSONANT_INDEX * VOWEL_INDEX),
            vowelIndex = (syllableIndex / CONSONANT_INDEX) % VOWEL_INDEX,
            finalIndex = (syllableIndex % CONSONANT_INDEX) - 1;

        char initConsonant = (char) (INIT_CONST_RANGE_START + initialIndex),
            vowel = (char) (VOWEL_RANGE_START + vowelIndex),
            finalConsonant = (finalIndex >= 0) 
                ? (char) (FINAL_CONST_RANGE_START + finalIndex) : '\0';

		switch (initConsonant) {
			case 0x1101:
			case 0x1104:
			case 0x1108:
			case 0x110A:
			case 0x110D:
				jamoCharacters.addAll(
    				Collections.nCopies(2, Character.toString((char)(initConsonant-1)))
    			);
    			break;
			default:
			    jamoCharacters.add(Character.toString(initConsonant));
				break;
		}

        switch (vowel) {
            case 0x116A:
            case 0x116B:
                jamoCharacters.add(Character.toString((char)(0x1169)));
                jamoCharacters.add(Character.toString((char)(vowel-9)));
                break;
            case 0x116C:
                jamoCharacters.add(Character.toString((char)(0x1169)));
                jamoCharacters.add(Character.toString((char)(0x1175)));
                break;
            case 0x116F:
            case 0x1170:
                jamoCharacters.add(Character.toString((char)(0x116E)));
                jamoCharacters.add(Character.toString((char)(vowel-10)));
                break;
            case 0x1171:
                jamoCharacters.add(Character.toString((char)(0x116E)));
                jamoCharacters.add(Character.toString((char)(0x1175)));
                break;
            case 0x1174:
                jamoCharacters.add(Character.toString((char)(vowel-1)));
                jamoCharacters.add(Character.toString((char)(vowel+1)));
                break;
            default:
                jamoCharacters.add(Character.toString(vowel));
                break;
        }

		if (finalConsonant != '\0') {
			switch (finalConsonant) {
				case 0x11A9:
				case 0x11BB:
					jamoCharacters.addAll(
						Collections.nCopies(2, Character.toString((char)(finalConsonant-1)))
					);
					break;
				case 0x11AA:
					jamoCharacters.add(Character.toString((char)0x11A8));
					jamoCharacters.add(Character.toString((char)0x11BA));
					break;
				case 0x11AC:
					jamoCharacters.add(Character.toString((char)0x11AB));
					jamoCharacters.add(Character.toString((char)0x11BD));
					break;
				case 0x11AD:
					jamoCharacters.add(Character.toString((char)0x11AB));
					jamoCharacters.add(Character.toString((char)0x11C2));
					break;
				case 0x11B0:
					jamoCharacters.add(Character.toString((char)0x11AF));
					jamoCharacters.add(Character.toString((char)0x11A8));
					break;
				case 0x11B1:
				case 0x11B2:
					jamoCharacters.add(Character.toString((char)0x11AF));
					jamoCharacters.add(Character.toString((char)(finalConsonant+6)));
					break;
				case 0x11B3:
					jamoCharacters.add(Character.toString((char)0x11AF));
					jamoCharacters.add(Character.toString((char)0x11BA));
					break;
				case 0x11B4:
				case 0x11B5:
				case 0x11B6:
					jamoCharacters.add(Character.toString((char)0x11AF));
					jamoCharacters.add(Character.toString((char)(finalConsonant+12)));
					break;
				case 0x11B9:
					jamoCharacters.add(Character.toString((char)(finalConsonant-1)));
					jamoCharacters.add(Character.toString((char)(finalConsonant+1)));
					break;
				default:
					jamoCharacters.add(Character.toString(finalConsonant));
					break;
			}
		}
        
        return jamoCharacters;
    }
    
	/**
	 * Converts a cluster array, with a sequential order of consonants and vowels, 
	 * 	to rebuild a Korean character
	 * @param clusters
	 * @return String
	 */
	public String convertClusterArrayToCharacter(List<KoreanSpeechCluster> clusters) {
		List<String> temporarySounds = new ArrayList<String>();
		clusters.forEach((c) -> temporarySounds.add(c.getLetter()));
		return this.convertLetterArrayToCharacter(temporarySounds);
	}

	/**
	 * Convert array of sequentially-placed consonants and vowels and build Korean character
	 * @param sounds
	 * @return String
	 */
    public String convertLetterArrayToCharacter(List<String> sounds) {
		int sLength = sounds.size();
		if (sLength < 2) {
		    System.out.println("Invalid input (need at least 2 elements in list). Try again!");
			return "";
		}
		
		List<Integer> unicodeValues = new ArrayList<>();
		
		for (int i = 0; i < sLength; i++) {
			String s = sounds.get(i);

			int parsed = (int) s.charAt(0);
			int uni = this.convertToProperUnicode(parsed, i);
			if (uni == -1) {
			    System.out.println("Invalid input (need list with proper Korean sounds). Try again!");
				return "";
			}
			
			if (unicodeValues.size() == 0) {
			    unicodeValues.add(uni);
			    continue;
			}
			
			int c = unicodeValues.size()-1;
			int comparable = unicodeValues.get(c);
		    if (uni == comparable) {
	            unicodeValues.remove(c);
		        unicodeValues.add(uni+1);
	        } else {
	            int compound = comparableCompoundChecker(comparable, uni);
	            boolean areVowel = (
	                withinRange(uni, VOWEL_RANGE_START, VOWEL_RANGE_END) && 
	                withinRange(comparable, VOWEL_RANGE_START, VOWEL_RANGE_END)
	            ), areFinal = (
	                withinRange(uni, FINAL_CONST_RANGE_START, FINAL_CONST_RANGE_END) && 
	                withinRange(comparable, FINAL_CONST_RANGE_START, FINAL_CONST_RANGE_END)
	            );

	            if (compound != -1 && (areFinal || areVowel)) {
	                unicodeValues.remove(c);
	                unicodeValues.add(compound);
	            } else {
	                unicodeValues.add(uni);
	            }
	        }
		}
		
		int a = unicodeValues.get(0) - INIT_CONST_RANGE_START; 
		int b = unicodeValues.get(1) - VOWEL_RANGE_START;
		int c = (unicodeValues.size() > 2) 
		    ? (unicodeValues.get(2) + 1) - FINAL_CONST_RANGE_START : 0;
		
		int result = (a * CONSONANT_INDEX * VOWEL_INDEX) + (b * CONSONANT_INDEX) + c;
		char temp = (char) (result+CHARACTER_RANGE_START);
		
		return Character.toString(temp);
	}
	
	/**
	 * Check condition such that requested number (n) is within range of start to end
	 * @param n
	 * @param start
	 * @param end
	 * @return boolean
	 */
	private boolean withinRange(int n, int start, int end) {
	    return n >= start && n <= end;
	}
	
	/**
	 * Compare two consonants or two vowels to match compound letter via lookup table
	 * @param i1
	 * @param i2
	 * @return int
	 */
	private int comparableCompoundChecker(int i1, int i2) {
		int[][] lookupTable = {
			{0x1169, 0x1161, 0x116A}, {0x1169, 0x1162, 0x116B},
			{0x1169, 0x1175, 0x116C}, {0x116E, 0x1165, 0x116F},
			{0x116E, 0x1166, 0x1170}, {0x116E, 0x1175, 0x1171},
			{0x1173, 0x1175, 0x1174}, {0x11A8, 0x11BA, 0x11AA},
			{0x11AB, 0x11BD, 0x11AC}, {0x11AB, 0x11C2, 0x11AD},
			{0x11AF, 0x11A8, 0x11B0}, {0x11AF, 0x11B7, 0x11B1},
			{0x11AF, 0x11B8, 0x11B2}, {0x11AF, 0x11BA, 0x11B3},
			{0x11AF, 0x11C0, 0x11B4}, {0x11AF, 0x11C1, 0x11B5},
			{0x11AF, 0x11C2, 0x11B6}, {0x11B8, 0x11BA, 0x11B9}
		};

		for (int[] row : lookupTable) {
			if (i1 == row[0] && i2 == row[1]) return row[2];
		}

		return -1;
	}
	
	/**
	 * Compare given unicode value to confirm that it is correct for hexadecimal operations
	 *  to rebuild Korean character. If already correct, assume that it is within established
	 *  range of either initial/final consonants, or vowels. 
	 * @param n
	 * @param i
	 * @return int
	 */
	private int convertToProperUnicode(int n, int i) {
	    if (
	        withinRange(n, FINAL_CONST_RANGE_START, FINAL_CONST_RANGE_END) || 
	        withinRange(n, VOWEL_RANGE_START, VOWEL_RANGE_END) || 
	        withinRange(n, INIT_CONST_RANGE_START, INIT_CONST_RANGE_END)
	    ) return n;
	    
	    boolean canBeFinal = (i>1), 
	        isVowel = (n >= 0x314F && n <= 0x3163);
	        
	    int[][] conversionTable = {
	        {0x3131, 0x1100, 0x11A8},   {0x3132, 0x1101, 0x11A9},
	        {0x3133, -1, 0x11AA},       {0x3134, 0x1102, 0x11AB},
	        {0x3135, -1, 0x11AC},       {0x3136, -1, 0x11AD},
	        {0x3137, 0x1103, 0x11AE},   {0x3138, 0x1104, -1},
	        {0x3139, 0x1105, 0x11AF},   {0x313A, -1, 0x11B0},
	        {0x313B, -1, 0x11B1},       {0x313C, -1, 0x11B2},
	        {0x313D, -1, 0x11B3},       {0x313E, -1, 0x11B4},
	        {0x313F, -1, 0x11B5},       {0x3140, -1, 0x11B6},
	        {0x3141, 0x1106, 0x11B7},   {0x3142, 0x1107, 0x11B8},
	        {0x3143, 0x1108, -1},       {0x3144, -1, 0x11B9 },
	        {0x3145, 0x1109, 0x11BA},   {0x3146, 0x110A, 0x11BB},
	        {0x3147, 0x110B, 0x11BC},   {0x3148, 0x110C, 0x11BD},
	        {0x3149, 0x110D, -1},       {0x314A, 0x110E, 0x11BE},
	        {0x314B, 0x110F, 0x11BF},   {0x314C, 0x1110, 0x11C0},
	        {0x314D, 0x1111, 0x11C1},   {0x314E, 0x1112, 0x11C2},
	        {0x314F, 0x1161, -1},       {0x3150, 0x1162, -1},
	        {0x3151, 0x1163, -1},       {0x3152, 0x1164, -1},
	        {0x3153, 0x1165, -1},       {0x3154, 0x1166, -1},
	        {0x3155, 0x1167, -1},       {0x3156, 0x1168, -1},
	        {0x3157, 0x1169, -1},       {0x3158, 0x116A, -1},
	        {0x3159, 0x116B, -1},       {0x315A, 0x116C, -1},
	        {0x315B, 0x116D, -1},       {0x315C, 0x116E, -1},
	        {0x315D, 0x116F, -1},       {0x315E, 0x1170, -1},
	        {0x315F, 0x1171, -1},       {0x3160, 0x1172, -1},
	        {0x3161, 0x1173, -1},       {0x3162, 0x1174, -1},
	        {0x3163, 0x1175, -1}
	    };
	    
	    for (int[] row : conversionTable) {
	        if (n == row[0]) return (isVowel || (!isVowel && !canBeFinal)) ? row[1] : row[2];
	    }
	    
	    return -1;
	}

	// ADD METHODS

	/**
	 * 
	 * @param curr
	 * @param letter
	 * @return List<String>
	 */
	protected List<String> addLetterToLetterArray(List<String> curr, String letter) {
		if (letter.length() < 1 || letter.length() > 2) {
			return curr;
		}
		return this.addLetterToLetterArray(curr, letter.charAt(0));
	}

	/**
	 * 
	 * @param curr
	 * @param letter
	 * @return List<String>
	 */
	protected List<String> addLetterToLetterArray(List<String> curr, char letter) {
		//
	}

	/**
	 * 
	 * @param curr
	 * @param cluster
	 * @return List<KoreanSpeechCluster>
	 */
	protected List<KoreanSpeechCluster> addClusterToClusterArray(
		List<KoreanSpeechCluster> curr, KoreanSpeechCluster cluster
	) {
		//
	}

	/**
	 * 
	 * @param curr
	 * @param character
	 * @return List<KoreanSpeechCluster>
	 */
	protected List<KoreanSpeechCluster> addLetterToClusterArray(
		List<KoreanSpeechCluster> curr, String character
	) {
		if (character.length() < 1 || character.length() > 2) {
			return curr;
		}
		return this.addLetterToClusterArray(curr, character.charAt(0));
	}

	/**
	 * 
	 * @param curr
	 * @param character
	 * @return List<KoreanSpeechCluster>
	 */
	protected List<KoreanSpeechCluster> addLetterToClusterArray(
		List<KoreanSpeechCluster> curr, char character
	) {
		//
	}

	// UPDATE METHODS

	protected List<String> updateLetterInLetterArray() {

	}

	protected List<String> updateLetterInLetterArray() {

	}

	protected List<KoreanSpeechCluster> updateClusterInClusterArray() {

	}

	protected List<KoreanSpeechCluster> updateLetterInClusterArray() {

	}

	protected List<KoreanSpeechCluster> updateLetterInClusterArray() {

	}

	// DELETE METHODS

	protected List<String> deleteLetterFromLetterArray() {

	}

	protected List<String> deleteLetterFromLetterArray() {
		
	}

	protected List<KoreanSpeechCluster> deleteClusterFromClusterArray() {

	}

	protected List<KoreanSpeechCluster> deleteLetterFromClusterArray() {

	}

	protected List<KoreanSpeechCluster> deleteLetterFromClusterArray() {
		
	}

}

class KoreanLetterConversionDemo {

	protected static KoreanLetterConversion klc = new KoreanLetterConversion();

	public static void demo(String a) {
		List<String> j = klc.convertCharacterToLetterArray(a.charAt(0));
		System.out.println("Array of sounds: " + j);
		System.out.println("Built-up character: " + klc.convertLetterArrayToCharacter(j));
		System.out.println();
	}

	public static void main(String[] args) {
		String[] arguments = new String[] { "환", "영", "합", "니", "다" };
		Arrays.stream(arguments).forEach(a -> demo(a));
	}
}