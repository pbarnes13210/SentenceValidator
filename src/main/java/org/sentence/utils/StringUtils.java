package org.sentence.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StringUtils {
    private static final Logger LOGGER = LogManager.getLogger(StringUtils.class.getName());
    public static char[] convertSentenceToCharArray(String sentence){
        if(sentence == null || sentence.isEmpty() ){
            LOGGER.warn("User has entered empty sentence");
            return null;
        }else {
            char[] sentenceArray = new char[sentence.length()];
            for (int i = 0; i < sentence.length(); i++) {
                sentenceArray[i] = sentence.charAt(i);
            }
            return sentenceArray;
        }
    }
}
