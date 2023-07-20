package com.stephull.projects.koreanbuildingapp.service;

import java.io.File;
import java.io.IOException;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import com.stephull.projects.koreanbuildingapp.koreanbuilding.DemoPrintConfig;

public class WikimediaHTMLConversion {
    
    private String charsetString = StandardCharsets.UTF_8.toString();
    private static int sectionIterationCount = 0;

    /**
     * 
     * @return Map<String, String>
     */
    private Map<String, String> fetchTextFromPTagProperty(String a, Element e) {
        Map<String, String> ret = new LinkedHashMap<String, String>();

        //

        return ret;
    }
    
    /**
     * 
     * @return Map<String, String>
     */
    private Map<String, String> fetchTextFromUlListProperty(String a, Element e) {
        Map<String, String> ret = new LinkedHashMap<String, String>();

        //

        return ret;
    }

    /**
     * 
     * @return Map<String, String>
     */
    private Map<String, String> fetchTextFromOlListProperty(String a, Element e) {
        Map<String, String> ret = new LinkedHashMap<String, String>();

        if (a.equalsIgnoreCase(HTMLPropertyType.NOUN)) {
            Element ol = e.select("ol").first();
            Elements bullets = ol.select("li");
                        
            int bulletIndex = 0;

            for (Element b : bullets) {
                // ::: get English definitions:
                // ... start with <a/> tags that indicate definition
                Elements aWithIdList = b.select("a[id^=mw]");
                if (aWithIdList != null) {
                    String aTemp; int aIndex = 0;
                    for (Element i : aWithIdList) {
                        String iText = i.text().trim();
                        if (iText.length() < 1) continue;
                        aTemp = sectionIterationCount + "_noun_word_meaning_" + bulletIndex + "_a_" + aIndex++;
                        ret.put(aTemp, iText);
                    }
                }
                // ... get plain text for rest of definition
                List<TextNode> textNodes = b.textNodes();
                if (textNodes != null) {
                    String tnTemp; int tnIndex = 0;
                    for (TextNode tn : textNodes) {
                        String tnText = tn.text().trim();
                        if (tnText.length() < 1) continue;
                        tnTemp = sectionIterationCount + "_noun_word_meaning_" + bulletIndex + "_text_" + tnIndex++;
                        ret.put(tnTemp, tnText);
                    }
                }
                // ... get any text in the parentheses
                Elements clarityElements = b.select("span.ib-content.qualifier-content");
                if (clarityElements != null) {
                    String cTemp; int cIndex = 0;
                    for (Element c : clarityElements) {
                        String cText = c.text().trim();
                        if (cText.length() < 1) continue;
                        cTemp = sectionIterationCount + "_noun_word_meaning_" + bulletIndex + "_tidbit_" + cIndex++;
                        ret.put(cTemp, cText);
                    }
                }

                // ::: get examples with Korean, roman., and translation:
                Element bulletDl = b.select("dl").first();
                if (bulletDl != null) {
                    Elements dlList = bulletDl.select("dd");
                    int dlIndex = 0;
                    for (Element dl : dlList) {
                        // ... Korean example
                        Element bulletContextKorean = dl.select("i.Kore.mention.e-example").first();
                        if (bulletContextKorean != null) {
                            ret.put(
                                sectionIterationCount + "_noun_korean_" + bulletIndex + "_" + dlIndex, 
                                bulletContextKorean.text()
                            );
                        }
                        // ... romanized
                        Element bulletContextRomanized = dl.select("i.e-transliteration.tr.Latn").first();
                        if (bulletContextRomanized != null) {
                            ret.put(
                                sectionIterationCount + "_noun_roman_" + bulletIndex + "_" + dlIndex, 
                                bulletContextRomanized.text()
                            );
                        }
                        // ... & translation
                        Element bulletContextTranslated = dl.select("span.e-translation").first();
                        if (bulletContextTranslated != null) {
                            ret.put(
                                sectionIterationCount + "_noun_translate_" + bulletIndex + "_" + dlIndex, 
                                bulletContextTranslated.text()
                            );
                        }
                        dlIndex++;
                    }
                }

                bulletIndex++;
            }
            return ret;
        }

        if (a.equalsIgnoreCase(HTMLPropertyType.PROPER_NOUN)) {
            return ret;
        }

        if (a.equalsIgnoreCase(HTMLPropertyType.REFERENCES)) {
            return ret;
        }

        return ret;
    }

    /**
     * 
     * @return Map<String, String>
     */
    private Map<String, String> fetchTextFromLiProperty(String a, Element e) {
        Map<String, String> ret = new LinkedHashMap<String, String>();

        //

        return ret;
    }

    /**
     * 
     * @return Map<String, String>
     */
    private Map<String, String> fetchTextFromTableProperty(String a, Element e) {
        Map<String, String> ret = new LinkedHashMap<String, String>();

        //

        return ret;
    }

    /**
     * 
     * @param head
     * @param e
     * @return Map.Entry<String, Map<String, String>>
     */
    private Map.Entry<String, Map<String, String>> fetchPropertiesFromSection(String head, Element e) {
        Map<String, String> value = new LinkedHashMap<String, String>();
        switch (head) {
            case HTMLPropertyType.NOUN:
            case HTMLPropertyType.PROPER_NOUN:
            case HTMLPropertyType.REFERENCES:
                value = this.fetchTextFromOlListProperty(head, e);
                break;
            case HTMLPropertyType.ETYMOLOGY:
                value = this.fetchTextFromPTagProperty(head, e);
                break;
            case HTMLPropertyType.PRONUNCIATION:
                value = this.fetchTextFromLiProperty(head, e);
                value.putAll(this.fetchTextFromUlListProperty(head, e));
                value.putAll(this.fetchTextFromTableProperty(head, e));
                break;
            case HTMLPropertyType.DERIVED_TERMS:
            case HTMLPropertyType.USAGE_NOTES:
                value = this.fetchTextFromUlListProperty(head, e);
                break;
            case HTMLPropertyType.NUMERAL:
            case HTMLPropertyType.SYLLABLE:
                value = this.fetchTextFromTableProperty(head, e);
                break;
            default:
                break;
        }

        String newHead = head + " " + sectionIterationCount;
        return Map.entry(newHead, value);
    }

    /**
     * 
     * @param doc
     * @return String[]
     */
    public Map<String, Map<String, String>> fetchPropertiesFromWikimediaHTML(Document doc) {
        Map<String, Map<String, String>> properties = new LinkedHashMap<String, Map<String, String>>();
        
        Elements sectionElements = doc.select("section");

        String defaultLang = "Korean";
        boolean isKorean = false;
        //int content_index = 0;

        for (Element section : sectionElements) {
            // if lang. is not Korean, skip section & go to next iteration/finish loop: 
            Element languageHeader = section.select("h2").first();
            if (languageHeader != null) {
                isKorean = languageHeader.text().equalsIgnoreCase(defaultLang);
                continue;
            }
            
            if (isKorean) {
                Element contentElement = section.select("h3, h4, h5").first();
                if (contentElement != null) {
                    // commented out: prints header of each section
                    //properties.put("test_" + content_index++, new String[]{ content.html() });
                    Map.Entry<String, Map<String, String>> prop = this.fetchPropertiesFromSection(
                        contentElement.text(), section
                    );
                    properties.put(prop.getKey(), prop.getValue());
                }
                sectionIterationCount++;
            }
        }

        return properties;
    }

    /**
     * Encodes non-English text to percent-encoding URI code
     * @param normalText
     * @return String
     */
    public String encodeURIPercentCode(String normalText) {
        try {
            boolean noLatin = checkOnlyNonLatinCharacters(normalText);
            return (noLatin) 
                ? URLEncoder.encode(normalText, charsetString)
                : normalText;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Decodes percent-encoding code to non-English text
     * @param encodedText
     * @return String
     */
    public String decodeURIPercentCode(String encodedText) {
        try {
            return URLDecoder.decode(encodedText, charsetString);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Check if the given text contains only non-English characters
     * If Latin letters are detected, return false
     * @param s
     * @return boolean
     */
    private boolean checkOnlyNonLatinCharacters(String s) {
        char[] characters = s.toCharArray();
        for (char c : characters) {
            if (c <= 127) return false;
        }
        return true;
    }

}

class WikimediaHTMLConversionDemo1 {

    private static WikimediaHTMLConversion whc = new WikimediaHTMLConversion();
    private static DemoPrintConfig dpc = new DemoPrintConfig();

    public static void demo(String entry) {
        String temp1 = whc.encodeURIPercentCode(entry);
        String temp2 = whc.decodeURIPercentCode(temp1);
        String result = (entry.equals(temp2)) ? "Same result" : "Failed to encode back";

        List<String> prints = new ArrayList<String>();
        prints.add("Original");
        prints.add(entry);
        prints.add("Encoded");
        prints.add(temp1);
        prints.add("Decoded");
        prints.add(temp2);
        prints.add("Result");
        prints.add(result);

        boolean written = dpc.writeToFile("test_percent_uri_encoding", ".txt", prints);
        if (written) {
            System.out.println("Log file for percent encoding is ready!");
        }
    }

    public static void main(String[] args) {
        String[] arguments = new String[] { "김치찌개", "らーめん", "担担麵", "Spaghetti" };
        Arrays.stream(arguments).forEach(arg -> demo(arg));
    }

}

class WikimediaHTMLConversionDemo2 {

    private static WikimediaHTMLConversion whc = new WikimediaHTMLConversion();
    private static DemoPrintConfig dpc = new DemoPrintConfig();

    private static String baseCwd = System.getProperty("user.dir").replace("\\", "/");
    private static String testFile = "kr_test_html_2.html";
    private static String htmlTestFilePath = new String(baseCwd+"/src/main/resources/data/" + testFile);

    public static void main(String[] args) {
        try {
            File htmlFile = new File(htmlTestFilePath);
            Document doc = Jsoup.parse(htmlFile, "UTF-8");

            Map<String, Map<String, String>> testMap = whc.fetchPropertiesFromWikimediaHTML(doc);
            List<String> printProperties = new ArrayList<String>();

            System.out.println("MAP OF HTML PROPERTIES");
            for (Map.Entry<String, Map<String, String>> entry : testMap.entrySet()) {
                String key = entry.getKey();
                Map<String, String> value = entry.getValue();

                printProperties.add("::: " + key + " :::");
                
                for (Map.Entry<String, String> innerEntry : value.entrySet()) {
                    printProperties.add(innerEntry.getKey());
                    printProperties.add(innerEntry.getValue());
                }
            }

            System.out.println("=====================");
            boolean writtenToFile = dpc.writeToFile("test_html_scrape", ".txt", printProperties); 
            if (writtenToFile) {
                System.out.println("Log file for HTML printing is ready!");
            }
        
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}