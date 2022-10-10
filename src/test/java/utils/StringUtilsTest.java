package utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.sentence.utils.StringUtils;

public class StringUtilsTest {

    @Test
    void convertSentenceToCharArray_ForEmptyString_ShouldReturnNull(){
        Assertions.assertNull(StringUtils.convertSentenceToCharArray(""));
    }

    @Test
    void convertSentenceToCharArray_ForNull_ShouldReturnNull(){
        Assertions.assertNull(StringUtils.convertSentenceToCharArray(null));
    }

    @Test
    void convertSentenceToCharArray_ForValidString_ShouldNotReturnNull(){
        String validString = "This is a valid string";
        Assertions.assertNotNull(StringUtils.convertSentenceToCharArray(validString));
    }
}
