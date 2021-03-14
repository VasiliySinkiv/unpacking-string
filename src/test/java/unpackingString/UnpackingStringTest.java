package unpackingString;

import org.junit.jupiter.api.Test;
import unpackingString.exception.UnpackingErrorCode;
import unpackingString.exception.UnpackingException;

import static org.junit.jupiter.api.Assertions.*;

class UnpackingStringTest {

    @Test
    public void testUnpackingValidString() {
        String sourceString = "3[xyz]4[xy]z";
        String expectedResultString = "xyzxyzxyzxyxyxyxyz";

        String resultString = null;
        try {
            resultString = UnpackingString.checkValidAndUnpackString(sourceString);
        } catch (UnpackingException exception) {
            exception.printStackTrace();
        }
        assertEquals(expectedResultString, resultString);
    }


    @Test
    public void testUnpackingValidStringWithAttachment() {
        String sourceString = "2[3[x]y]";
        String expectedResultString = "xxxyxxxy";

        String resultString = null;
        try {
            resultString = UnpackingString.checkValidAndUnpackString(sourceString);
        } catch (UnpackingException exception) {
            exception.printStackTrace();
        }
        assertEquals(expectedResultString, resultString);
    }


    @Test
    public void testUnpackingInvalidStringWithPoint() {
        String sourceString = "3[xyz.]4[xy]z";

        try {
            UnpackingString.checkValidAndUnpackString(sourceString);
        } catch (UnpackingException exception) {
            assertEquals(UnpackingErrorCode.WRONG_CHARACTERS, exception.getErrorCode());
        }
    }


    @Test
    public void testUnpackingInvalidStringWithoutCloseBracket() {
        String sourceString = "3[xyz4[xy]z";

        try {
            UnpackingString.checkValidAndUnpackString(sourceString);
        } catch (UnpackingException exception) {
            assertEquals(UnpackingErrorCode.WRONG_OPEN_BRACKET, exception.getErrorCode());
        }
    }


    @Test
    public void testUnpackingInvalidStringWithoutOpenBracket() {
        String sourceString = "3[xyz]4xy]z";

        try {
            UnpackingString.checkValidAndUnpackString(sourceString);
        } catch (UnpackingException exception) {
            assertEquals(UnpackingErrorCode.WRONG_CLOSE_BRACKET, exception.getErrorCode());
        }

    }

}