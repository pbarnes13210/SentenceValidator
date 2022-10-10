package validation;

import org.junit.jupiter.api.BeforeAll;
import org.sentence.inputManager.SentenceManager;
import org.sentence.validation.SentenceValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SentenceValidatorTest {
    private SentenceValidator sentenceValidator;
    private SentenceManager sentenceManager;

    @BeforeEach
    void setup(){
        sentenceValidator= new SentenceValidator();
        sentenceManager = new SentenceManager(new SentenceValidator());
    }

    @Test
    void validateCapitalLetters_ReturnsTrueIfSentenceStartsWithCapitalLetter(){
        String test = "This sentence is used to test if sentence starts with capital letter";
        char[] sentence = convertStringSentenceToCharArray(test);
        assertTrue(sentenceValidator.validateStringStartsWithCapitalLetter(sentence));

    }

    @Test
    void validateCapitalLetters_ReturnFalseIfSentenceDoesNotStartWithCapitalLetter(){
        String test = "this sentence is used to test if sentence does not start with capital letter";
        char[] sentence = convertStringSentenceToCharArray(test);
        assertFalse(sentenceValidator.validateStringStartsWithCapitalLetter(sentence));
    }

    // This test tests all capital letters
    @Test
    void validateCapitalLetters_ValidateAllCapitalLettersInPropertiesFileReturnTrueIfAtStartOfSentence(){
        String[] capitalLetters = new String[] {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","X","Z"};
        String test = "this sentence tests all the capital letters in property file";

        // Take first character away from string
        int stringSize = test.length();
        test = test.substring(1,stringSize);


        // Loop through character array and replace start of sentence string with each Capital Letter
        for (String startingLetter : capitalLetters) {

            // Amend sentence with new starting letter
            String fullSentence = startingLetter + test;

            // Convert to char array
            char[] sentence1 = convertStringSentenceToCharArray(fullSentence);

            // Test each capital letter symbol from properties file is valid
            assertTrue(sentenceValidator.validateStringStartsWithCapitalLetter(sentence1));
        }
    }

    @Test
    void validateQuotationMarks_ReturnTrueIfSentenceHasEvenNumberOfQuotationMarks(){
        String test = "This sentence is used to test if number of \"quotation marks\" is equal ";
        char[] sentence = convertStringSentenceToCharArray(test);
        assertTrue(sentenceValidator.validateQuotationMarks(sentence));
    }

    @Test
    void validateQuotationMarks_ReturnFalseIfSentenceHasOddNumberOfQuotationMarks(){
        String test = "This sentence is used to test if number of quotation marks \"quotation marks\" is odd \"";
        char[] sentence = convertStringSentenceToCharArray(test);
        assertFalse(sentenceValidator.validateQuotationMarks(sentence));
    }

    @Test
    void validateTerminationCharacters_ReturnTrueIfSentenceEndsWithValidTerminationCharacter(){
        String test = "This sentence is used to test if it ends with a termination character method.";
        char[] sentence = convertStringSentenceToCharArray(test);
        assertTrue(sentenceValidator.validateTerminationCharacters(sentence));
    }

    @Test
    void validateTerminationCharacters_ReturnFalseIfSentenceDoesNotEndsWithValidTerminationCharacter_Period(){
        String test = "This sentence is used to test if it does not end with a termination character ";
        char[] sentence = convertStringSentenceToCharArray(test);
        assertFalse(sentenceValidator.validateTerminationCharacters(sentence));
    }

    @Test
    void validateTerminationCharacters_ReturnTrueIfSentenceEndsWithValidTerminationCharacter_ExclamationMark(){
        String test = "This sentence is used to test if it ends with a termination character method!";
        char[] sentence = convertStringSentenceToCharArray(test);
        assertTrue(sentenceValidator.validateTerminationCharacters(sentence));
    }


    @Test
    void validateTerminationCharacters_ReturnTrueIfSentenceEndsWithValidTerminationCharacter_QuestionMark(){
        String test = "This sentence is used to test if it ends with a termination character method?";
        char[] sentence = convertStringSentenceToCharArray(test);
        assertTrue(sentenceValidator.validateTerminationCharacters(sentence));
    }

    @Test
    void validatePeriodCharacters_ReturnTrueIfSentenceHasValidPositionOfPeriodCharacters(){
        String test = "This sentence is used to test if it does not have a period character other than last character.";
        char[] sentence = convertStringSentenceToCharArray(test);
        assertTrue(sentenceValidator.validatePeriodCharacters(sentence));
    }

    @Test
    void validatePeriodCharacters_ReturnFalseIfSentenceDoesNotHaveValidPositionOfPeriodCharacters(){
        String test = "This sentence is used to test if it has a period character. other than last character";
        char[] sentence = convertStringSentenceToCharArray(test);
        assertFalse(sentenceValidator.validatePeriodCharacters(sentence));
    }

    @Test
    void validateNumbers_ReturnTrueIfSentenceDoesNotHaveIntegerNumbersBelow13(){
        String sentence = "This sentence is used to test if it has no invalid numbers";
        assertTrue(sentenceValidator.validateNumbers(sentence));
    }

    @Test
    void validateNumbers_ReturnFalseIfSentenceHasInvalidIntegerNumbersBelow13(){
        String sentence = "This sentence is used to test if it has integer numbers below 13 such as 1,2,3 and 4";
        assertFalse(sentenceValidator.validateNumbers(sentence));
    }

    @Test
    void validate_ValidExampleOneFromQuestion_ShouldReturnTrue(){
        String validExampleFromQuestion = "The quick brown fox said “hello Mr lazy dog”.";
        char[] sentence = convertStringSentenceToCharArray(validExampleFromQuestion);
        assertTrue(sentenceManager.validateSentence(sentence,validExampleFromQuestion));
    }

    @Test
    void validate_ValidExampleTwoFromQuestion_ShouldReturnTrue(){
        String validExampleFromQuestion = "The quick brown fox said hello Mr lazy dog.";
        char[] sentence = convertStringSentenceToCharArray(validExampleFromQuestion);
        assertTrue(sentenceManager.validateSentence(sentence,validExampleFromQuestion));
    }

    @Test
    void validate_ValidExampleThreeFromQuestion_ShouldReturnTrue(){
        String validExampleFromQuestion = "One lazy dog is too few, 13 is too many.";
        char[] sentence = convertStringSentenceToCharArray(validExampleFromQuestion);
        assertTrue(sentenceManager.validateSentence(sentence,validExampleFromQuestion));
    }

    @Test
    void validate_ValidExampleFourThreeFromQuestion_ShouldReturnTrue(){
        String validExampleFromQuestion = "One lazy dog is too few, thirteen is too many.";
        char[] sentence = convertStringSentenceToCharArray(validExampleFromQuestion);
        assertTrue(sentenceManager.validateSentence(sentence,validExampleFromQuestion));
    }

    @Test
    void validate_ValidExampleFiveThreeFromQuestion_ShouldReturnTrue(){
        String validExampleFromQuestion = "How many \"lazy dogs\" are there?";
        char[] sentence = convertStringSentenceToCharArray(validExampleFromQuestion);
        assertTrue(sentenceManager.validateSentence(sentence,validExampleFromQuestion));
    }

    @Test
    void validate_InvalidExampleOneFromQuestion_ShouldReturnFalse(){
        String validExampleFromQuestion = "The quick brown fox said \"hello Mr. lazy dog\".";
        char[] sentence = convertStringSentenceToCharArray(validExampleFromQuestion);
        assertFalse(sentenceManager.validateSentence(sentence,validExampleFromQuestion));
    }

    @Test
    void validate_InvalidExampleTwoFromQuestion_ShouldReturnFalse(){
        String validExampleFromQuestion = "the quick brown fox said “hello Mr lazy dog\".";
        char[] sentence = convertStringSentenceToCharArray(validExampleFromQuestion);
        assertFalse(sentenceManager.validateSentence(sentence,validExampleFromQuestion));
    }

    @Test
    void validate_InvalidExampleThreeFromQuestion_ShouldReturnFalse(){
        String validExampleFromQuestion = "\"The quick brown fox said “hello Mr lazy dog.\"";
        char[] sentence = convertStringSentenceToCharArray(validExampleFromQuestion);
        assertFalse(sentenceManager.validateSentence(sentence,validExampleFromQuestion));
    }

    @Test
    void validate_InvalidExampleFourThreeFromQuestion_ShouldReturnFalse(){
        String validExampleFromQuestion = "One lazy dog is too few, 12 is too many";
        char[] sentence = convertStringSentenceToCharArray(validExampleFromQuestion);
        assertFalse(sentenceManager.validateSentence(sentence,validExampleFromQuestion));
    }

    @Test
    void validate_InvalidExampleFiveThreeFromQuestion_ShouldReturnFalse(){
        String validExampleFromQuestion = "Are there 11, 12, or 13 lazy dogs?";
        char[] sentence = convertStringSentenceToCharArray(validExampleFromQuestion);
        assertFalse(sentenceManager.validateSentence(sentence,validExampleFromQuestion));
    }

    @Test
    void validate_InvalidExampleSixThreeFromQuestion_ShouldReturnFalse(){
        String validExampleFromQuestion = "There is no punctuation in this sentence";
        char[] sentence = convertStringSentenceToCharArray(validExampleFromQuestion);
        assertFalse(sentenceManager.validateSentence(sentence,validExampleFromQuestion));
    }
    char[] convertStringSentenceToCharArray(String sentence){
        char[] sentenceArray = new char[sentence.length()];
        for(int i= 0; i <sentence.length(); i++) {
            sentenceArray[i] = sentence.charAt(i);
        }
        return sentenceArray;
    }
}
