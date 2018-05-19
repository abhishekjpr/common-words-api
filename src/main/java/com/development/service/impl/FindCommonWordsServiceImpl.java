package com.development.service.impl;

import com.development.service.FindCommonWordsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Service
@Slf4j
public class FindCommonWordsServiceImpl implements FindCommonWordsService {

    private static int incrCount = 0;

    @Override
    public HashMap<String, Integer> findWords(String path) {
        HashMap<String, Integer> result = null;
        try {
            result = getCommonWords(path);
            if(result.size() != 0) {
                log.info("Common Words are :: \n");
                Set set = result.keySet();
                Iterator<String> iter = set.iterator();
                while (iter.hasNext()) {
                    System.out.print(iter.next() + " ");
                }
            } else {
                log.info("\nNo Common Words.");
            }
        } catch (IOException e) {
            log.error("Error in reading path.");
        }
        return result;
    }

    private static HashMap<String, Integer> getCommonWords(String path) throws IOException {
        File[] file = new File(path).listFiles();
        HashMap<String, Integer> result = new HashMap<String, Integer>();
        Scanner scanFile = null;
        String theWord = new String();
        int count = 1;
        for(int i = 0; i < file.length; ++i) {
            try {
                scanFile = new Scanner(new FileReader(file[i]));
                while (scanFile.hasNext()){
                    theWord = scanFile.next().toLowerCase();
                    theWord = removeSpecialCharacters(theWord);
                    if(i == 0) {
                        result.put(theWord, 1);
                    } else {
                        if(result.containsKey(theWord)){
                            if(result.get(theWord) == count)
                                result.put(theWord, result.get(theWord)+1);
                        }
                    }
                }
                if(i != 0) {
                    incrCount = incrCount+1;
                    count = count + 1;
                    removeUnsedWordFromMap(result, incrCount);
                }
            } catch (Exception e) {
                System.out.println("Exception in reading file."+file[0].getAbsolutePath());
            }
        }
        return result;
    }

    private static String removeSpecialCharacters(String word){
        return word.replaceAll("[^a-zA-Z0-9]", "");
    }

    private static void removeUnsedWordFromMap(HashMap<String, Integer> result, Integer incrCount) {

        Iterator<Map.Entry<String, Integer>> entryIt = result.entrySet().iterator();
        while (entryIt.hasNext()) {
            Map.Entry<String, Integer> entry = entryIt.next();
            if (entry.getValue() == incrCount) {
                entryIt.remove();
            }
        }
    }
}
