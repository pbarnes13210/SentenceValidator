package org.sentence.inputManager;

public interface ISentenceManager {
    void initialise();
    boolean validateSentence(char[] sentenceArray, String sentence);
    void receiveSentence();
}
