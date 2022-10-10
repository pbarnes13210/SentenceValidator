package org.sentence.inputManager;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sentence.utils.StringUtils;
import org.sentence.validation.SentenceValidator;

public class SentenceManager implements ISentenceManager {
    private static final Logger LOGGER = LogManager.getLogger(SentenceManager.class.getName());
    private SentenceValidator sentenceValidator;
    private Scanner scan;
    private boolean sentenceStartsWithCapitalLetter;
    private boolean sentenceValidQuotationMarks;
    private boolean sentenceEndsWithValidTerminationCharacters;
    private boolean sentenceHasValidPeriodCharacterPositions;
    private boolean sentenceHasValidNumbers;
    public SentenceManager(){
        initialise();
    }

    @Override
    public void initialise() {
        this.scan = new Scanner(System.in);
        this.sentenceValidator = new SentenceValidator();
    }

    @Override
    public void receiveSentence() {
        System.out.println("Please Enter Sentence");

        // Take user input from console
        String sentence = scan.nextLine();

        // Pass String to utility class and convert to char array
        char[] sentenceArray= StringUtils.convertSentenceToCharArray(sentence);

        // if input data / user sentence is not null, pass to validator
        if(!(sentence==null && sentenceArray == null)){
            if(validateSentence(sentenceArray, sentence))
                System.out.println("Sentence is valid");
        }else{
            LOGGER.warn("No sentence to validate");
        }
    }

    @Override
    public boolean validateSentence(char[] sentenceArray, String sentence) {
        if(sentenceValidator.validateStringStartsWithCapitalLetter(sentenceArray))
            sentenceStartsWithCapitalLetter = true;

        if(sentenceValidator.validateQuotationMarks(sentenceArray))
            sentenceValidQuotationMarks = true;

        if(sentenceValidator.validateTerminationCharacters(sentenceArray))
            sentenceEndsWithValidTerminationCharacters = true;

        if(sentenceValidator.validatePeriodCharacters(sentenceArray))
            sentenceHasValidPeriodCharacterPositions = true;

        if(sentenceValidator.validateNumbers(sentence))
            sentenceHasValidNumbers = true;

        return sentenceStartsWithCapitalLetter && sentenceValidQuotationMarks
                && sentenceEndsWithValidTerminationCharacters && sentenceHasValidPeriodCharacterPositions
                && sentenceHasValidNumbers;
    }

    public static void main(String[] args) {
        LOGGER.warn("Application has started");
        SentenceManager inputManager = new SentenceManager();
        inputManager.receiveSentence();
    }
}