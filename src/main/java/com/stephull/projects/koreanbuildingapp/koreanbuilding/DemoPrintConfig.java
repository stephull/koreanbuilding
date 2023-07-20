package com.stephull.projects.koreanbuildingapp.koreanbuilding;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import java.nio.charset.StandardCharsets;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

public class DemoPrintConfig {
    
    private String baseCwd = System.getProperty("user.dir").replace("\\", "/");
    private String testResultsDir = baseCwd + "/src/main/resources/data/test_results/";

    /**
     * 
     * @return String
     */
    private String getTimeStamp() {
        long currentTimeStamp = System.currentTimeMillis();
        String dateFormat = "yyyy-MM-dd_HH-mm-ss";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date date = new Date(currentTimeStamp);
        String formattedDate = sdf.format(date);
        return ("-" + formattedDate);
    }

    /**
     * 
     * @param key
     * @param format
     * @param arguments
     * @return boolean
     */
    public boolean writeToFile(String key, String format, List<String> arguments) {
        String ts = this.getTimeStamp();
        String newFilePath = testResultsDir + key + ts + format;
        
        File file = new File(newFilePath);

        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        BufferedWriter br = null;

        try {
            file.getParentFile().mkdirs();
            if (!file.exists()) {
                file.createNewFile();
            }
            
            fos = new FileOutputStream(newFilePath, true);
            osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
            br = new BufferedWriter(osw);

            for (int i = 0; i < arguments.size(); i++) {
                String a = arguments.get(i);
                //boolean conditionalFormat = (i > 0 && i % 2 != 0);
                br.write(a + '\n'); // + (conditionalFormat ? "\n" : ""));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null && osw != null && fos != null) {
                    br.close();
                    fos.close();
                    osw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }

}
