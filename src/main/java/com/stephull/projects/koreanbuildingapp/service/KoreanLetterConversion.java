package com.stephull.projects.koreanbuildingapp.service;

import com.stephull.projects.koreanbuildingapp.data.KoreanBuildData;
import com.stephull.projects.koreanbuildingapp.data.KoreanUnicodeProvider;
import com.stephull.projects.koreanbuildingapp.model.KoreanSpeechCluster;
import com.stephull.projects.koreanbuildingapp.model.SpeechType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KoreanLetterConversion {

	@Autowired
	private final KoreanUnicodeProvider kup;

	@Autowired
	private final KoreanBuildData kbdata;

	public KoreanLetterConversion(
		KoreanUnicodeProvider kup,
		KoreanBuildData kbdata
	) {
		this.kup = kup;
		this.kbdata = kbdata;
	}

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
		int[] initRange = new int[] { 
			kup.getNormalJamoConsonantRangeStart(), kup.getNormalJamoConsonantRangeEnd() 
		};
		int[] vowelRange = new int[] { 
			kup.getNormalJamoVowelRangeStart(), kup.getNormalJamoVowelRangeEnd() 
		};
		int[] finalRange = new int[] { 
			kup.getNormalJamoEndConsonantRangeStart(), kup.getNormalJamoEndConsonantRangeEnd() 
		};

		List<KoreanSpeechCluster> clusters = new ArrayList<KoreanSpeechCluster>();

		List<String> sounds = this.convertCharacterToLetterArray(character);
		for (String s : sounds) {
			int soundCode = (int) s.charAt(0);

			// ... for romanization of sound
			// (maybe fetch from database or speech-synthesis API ?!?)
			//String roman = kbrepo.findSoundRomanizationByLetter(s);

			String st;
			if (this.withinRange(soundCode, initRange[0], initRange[1])) {
				st = SpeechType.CONSONANT;
			} else if (this.withinRange(soundCode, vowelRange[0], vowelRange[1])) {
				st = SpeechType.VOWEL;
			} else if (this.withinRange(soundCode, finalRange[0], finalRange[1])) {
				st = SpeechType.END_CONSONANT;
			} else {
				st = null;
			}

			// 
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
		int[] syllableRange = new int[] {
			kup.getSyllableCharacterRangeStart(), kup.getSyllableCharacterRangeEnd()
		};
		int consonantIndex = kup.getConsonantCount();
		int vowelIndex = kup.getVowelCount();

        List<String> jamoCharacters = new ArrayList<String>();

        int charCode = (int) character;
        if (charCode < syllableRange[0] || charCode > syllableRange[1]) {
            System.out.println("Invalid input (need proper Korean character). Try again!");
			return jamoCharacters; 
        }        
        
        int syllableIndex = charCode - syllableRange[0];
        int initialPlacement = syllableIndex / (consonantIndex * vowelIndex),
            vowelPlacement = (syllableIndex / consonantIndex) % vowelIndex,
            finalPlacement = (syllableIndex % consonantIndex) - 1;

        char initConsonant = (char) (kup.getNormalJamoConsonantRangeStart() + initialPlacement),
            vowel = (char) (kup.getNormalJamoVowelRangeStart() + vowelPlacement),
            finalConsonant = (finalPlacement >= 0) 
                ? (char) (kup.getNormalJamoEndConsonantRangeStart() + finalPlacement) 
				: '\0';

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

				int[] vowelRange = new int[] {
					kup.getNormalJamoVowelRangeStart(), kup.getNormalJamoVowelRangeEnd()
				};
				int[] finalRange = new int[] {
					kup.getNormalJamoEndConsonantRangeStart(), kup.getNormalJamoEndConsonantRangeEnd()
				};

	            boolean areVowel = (
	                withinRange(uni, vowelRange[0], vowelRange[1]) && 
	                withinRange(comparable, vowelRange[0], vowelRange[1])
	            ), areFinal = (
	                withinRange(uni, finalRange[0], finalRange[1]) && 
	                withinRange(comparable, finalRange[0], finalRange[1])
	            );

	            if (compound != -1 && (areFinal || areVowel)) {
	                unicodeValues.remove(c);
	                unicodeValues.add(compound);
	            } else {
	                unicodeValues.add(uni);
	            }
	        }
		}
		
		int a = unicodeValues.get(0) - kup.getNormalJamoConsonantRangeStart(); 
		int b = unicodeValues.get(1) - kup.getNormalJamoVowelRangeStart();
		int c = (unicodeValues.size() > 2) 
		    ? (unicodeValues.get(2) + 1) - kup.getNormalJamoEndConsonantRangeStart() 
			: 0;
		
		int consonantIndex = kup.getConsonantCount();
		int vowelIndex = kup.getVowelCount();

		int result = (a * consonantIndex * vowelIndex) + (b * consonantIndex) + c;
		char temp = (char) (result + kup.getSyllableCharacterRangeStart());
		
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
		for (int[] row : kbdata.getCompoundComparisonTable()) {
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
		int[] initRange = new int[] { 
			kup.getNormalJamoConsonantRangeStart(), kup.getNormalJamoConsonantRangeEnd() 
		};
		int[] vowelRange = new int[] { 
			kup.getNormalJamoVowelRangeStart(), kup.getNormalJamoVowelRangeEnd() 
		};
		int[] finalRange = new int[] { 
			kup.getNormalJamoEndConsonantRangeStart(), kup.getNormalJamoEndConsonantRangeEnd() 
		};

	    if (
	        withinRange(n, initRange[0], initRange[1]) || 
	        withinRange(n, vowelRange[0], vowelRange[1]) || 
	        withinRange(n, finalRange[0], finalRange[1])
	    ) return n;
	    
	    boolean canBeFinal = (i>1), isVowel = (n >= 0x314F && n <= 0x3163);

	    for (int[] row : kbdata.getUnicodeConversionTable()) {
	        if (n == row[0]) {
				return (isVowel || (!isVowel && !canBeFinal)) ? row[1] : row[2];
			};
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
		if (letter.length() < 1 || letter.length() > 1) return curr;
		curr.add(letter);
		return curr;
	}

	/**
	 * 
	 * @param curr
	 * @param letter
	 * @return List<String>
	 */
	protected List<String> addLetterToLetterArray(List<String> curr, char letter) {
		return this.addLetterToLetterArray(curr, Character.toString(letter));
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
		// FIX THIS
		return new ArrayList<>();
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
		if (character.length() < 1 || character.length() > 1) return curr;

		KoreanSpeechCluster newKsc = new KoreanSpeechCluster();
		newKsc.setLetter(character);

		// other logic...

		curr.add(newKsc);
		return curr;
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
		return this.addLetterToClusterArray(curr, Character.toString(character));
	}

	/**
	 * 
	 * @param build
	 * @param letter
	 * @param index
	 * @return
	 */
	protected String updateCharacterWithAdditionalLetter(String build, String letter, int index) {
		if (letter.length() < 1 || letter.length() > 1) return build;
		return new String("");
	}

	/**
	 * 
	 * @param build
	 * @param character
	 * @param index
	 * @return
	 */
	protected String updateCharacterWithAdditionalLetter(String build, char character, int index) {
		return this.updateCharacterWithAdditionalLetter(build, Character.toString(character), index);
	}

	/**
	 * 
	 * @param build
	 * @param original
	 * @param replacement
	 * @return
	 */
	protected String updateCharacterWithChangeInLetter(String build, String original, String replacement) {
		if (original.length() < 1 || original.length() > 1) return build;
		return new String("");
	}

	/**
	 * 
	 * @param build
	 * @param original
	 * @param replacement
	 * @return
	 */
	protected String updateCharacterWithChangeInLetter(String build, char original, char replacement) {
		String o = Character.toString(original), r = Character.toString(replacement);
		return updateCharacterWithChangeInLetter(build, o, r);
	}

	/**
	 * 
	 * @param build
	 * @param wantRemoved
	 * @return
	 */
	protected String updateCharacterWithRemovalOfLetter(String build, String wantRemoved) {
		if (wantRemoved.length() < 1 || wantRemoved.length() > 2) return build;
		return new String("");
	}

	/**
	 * 
	 * @param build
	 * @param wantRemoved
	 * @return
	 */
	protected String updateCharacterWithRemovalOfLetter(String build, char wantRemoved) {
		return this.updateCharacterWithRemovalOfLetter(build, Character.toString(wantRemoved));
	}

	// UPDATE METHODS

	protected List<String> updateLetterInLetterArray(List<String> letters, String patch, int index) {
		if (patch.length() < 1 || patch.length() > 1) return letters;
		
		//

		return letters;
	}

	protected List<String> updateLetterInLetterArray(List<String> letters, char patch, int index) {
		return this.updateLetterInLetterArray(letters, Character.toString(patch), index);
	}	

	protected List<KoreanSpeechCluster> updateClusterInClusterArray() {
		// IDK
		return new ArrayList<>();
	}

	protected List<KoreanSpeechCluster> updateLetterInClusterArray(List<KoreanSpeechCluster> clusters, char patch, int index) {
		return this.updateLetterInClusterArray(clusters, Character.toString(patch), index);
	}

	protected List<KoreanSpeechCluster> updateLetterInClusterArray(List<KoreanSpeechCluster> clusters, String patch, int index) {
		if (patch.length() < 1 || patch.length() > 1) return clusters;
	
		//	

		return clusters;
	}

	// DELETE METHODS

	protected List<String> deleteLetterFromLetterArray(List<String> letters, String wantRemoved) {
		if (wantRemoved.length() < 1 || wantRemoved.length() > 1) return letters;
		
		//

		return letters;
	}

	protected List<String> deleteLetterFromLetterArray(List<String> letters, char wantRemoved) {
		return this.deleteLetterFromLetterArray(letters, Character.toString(wantRemoved));
	}

	protected List<KoreanSpeechCluster> deleteClusterFromClusterArray() {
		// IDK ?!?
		return new ArrayList<>();
	}

	protected List<KoreanSpeechCluster> deleteLetterFromClusterArray(List<KoreanSpeechCluster> clusters, String wantRemoved) {
		if (wantRemoved.length() < 1 || wantRemoved.length() > 1) return clusters;

		//

		return clusters;
	}

	protected List<KoreanSpeechCluster> deleteLetterFromClusterArray(List<KoreanSpeechCluster> clusters, char wantRemoved) {
		return this.deleteLetterFromClusterArray(clusters, Character.toString(wantRemoved));
	}

}

class KoreanLetterConversionDemo {

	protected static KoreanLetterConversion klc = new KoreanLetterConversion(
		new KoreanUnicodeProvider(), new KoreanBuildData()
	);

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