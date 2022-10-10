package org.sentence.validation;

import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sentence.utils.Constants;
import org.sentence.utils.FileUtils;

public class SentenceValidator {
    private static final Logger LOGGER = LogManager.getLogger(SentenceValidator.class.getName());
    private static final String SENTENCE_VALIDATION_ERROR = "SentenceValidationError";
    public Properties properties = new Properties();
    private boolean capitalLetter;
    private boolean incorrectPeriodCharacterPosition;
    private boolean endsWithTerminationCharacter;
    private boolean invalidNumbers;
    private int quotationMarkCount = 0;

    public SentenceValidator(){
        FileUtils.findPropertiesFile(properties);
    }
    public boolean validateStringStartsWithCapitalLetter(char[] sentence) {
        // Take capital letter values from property file
        String[] capitalLetterValues = properties.getProperty(Constants.PROPERTY_CAPITAL_LETTERS).split(",");

        // Loop through capital letters and check if they contain array index 1 of sentence
        for (String capitalLetterValue : capitalLetterValues) {
            if (capitalLetterValue.contains(String.valueOf(sentence[0]))) {
                capitalLetter = true;
                break;
            }
        }

        if(!capitalLetter){
            logSentenceValidationError("Starting letter is not a capital letter ", String.valueOf(sentence[0]), true);
            return false;
        }else{
            return true;
        }
    }
    public boolean validateQuotationMarks(char[] sentence){
        // Take quotation mark symbol from property file
        String quotationMarks = properties.getProperty(Constants.PROPERTY_QUOTATION_MARKS);

        // Loop through sentence array and count how many quotation mark symbols it contains
        for (char c : sentence) {
            if (c == quotationMarks.charAt(0)) {
                quotationMarkCount++;
            }
        }

        // Check if final count value can be divided by 2, if not then number is odd
        if(!(quotationMarkCount % 2 ==0)){
            logSentenceValidationError("Sentence has odd number of quotation marks", String.valueOf(sentence), true);
            return false;
        }else{
            return true;
        }
    }
    public boolean validateTerminationCharacters(char[] sentence) {
        // Take termination character symbols from property file
        String[] terminationCharacters = properties.getProperty(Constants.PROPERTY_TERMINATION_CHARACTERS).split(",");

        // Loop through termination characters array and check if it contains the last character of sentence
        int sentenceSize = sentence.length;
        for (String terminationCharacter : terminationCharacters) {
            if (terminationCharacter.contains(String.valueOf(sentence[sentenceSize - 1]))) {
                endsWithTerminationCharacter = true;
                return true;
            }
        }

        if(!endsWithTerminationCharacter){
            logSentenceValidationError("Sentence does not end with a termination character ( .,?,! )", String.valueOf(sentence[sentenceSize-1]), true);
            return false;
        }else{
            return true;
        }
    }
    public boolean validatePeriodCharacters(char[] sentence) {
        // Take period character symbol from property file
        String periodCharacter = properties.getProperty(Constants.PROPERTY_PERIOD_CHARACTERS);

        // Loop through sentence up until the second last index and check if it contains a period character
        int sentenceSize = sentence.length;
        for (int i = 0; i < sentenceSize - 1; i++) {
            if (sentence[i] == periodCharacter.charAt(0)) {
                incorrectPeriodCharacterPosition = true;
                break;
            }
        }

        if(incorrectPeriodCharacterPosition){
            logSentenceValidationError("Sentence has period characters other than last position ", String.valueOf(sentence), true);
            return false;
        }else{
            return true;
        }
    }
    public boolean validateNumbers(String sentence){
        // Take number integers and characters from property file
        String[] invalidNumbersBelow13 = properties.getProperty(Constants.PROPERTY_INVALID_NUMBER_CHARACTERS_BELOW_13).split(",");

        // Remove any commas in String
        String newSentence = sentence.replace(","," ");

        // Store user sentence as array list
        List<String> sentenceArrayAsList = Arrays.asList(newSentence.split(" "));

        // Loop through invalid numbers array and check if array list contains any invalid numbers
        for (String s : invalidNumbersBelow13) {
            if (sentenceArrayAsList.contains(s)) {
                invalidNumbers = true;
                break;
            }
        }

        if(invalidNumbers){
            logSentenceValidationError("Sentence contains invalid numbers", sentence, true);
            return false;
        }else{
            return true;
        }
    }

    protected void logSentenceValidationError(String message, String errorValue, boolean errorLevel){
        String logMessage = SENTENCE_VALIDATION_ERROR + ", message= " + message + ", errorValue= " + errorValue;

        if(errorLevel)
            LOGGER.error(logMessage);
        else
            LOGGER.warn(logMessage);
    }
}
