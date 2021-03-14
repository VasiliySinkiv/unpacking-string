package unpackingString;

import unpackingString.exception.UnpackingErrorCode;
import unpackingString.exception.UnpackingException;

import java.util.Arrays;
import java.util.Stack;

public class UnpackingString {

    private static final String CORRECT_CHARSETS = "'0-9' '[' ']' 'a-zA-Z'";
    private static final char OPEN_BRACKET = '[';
    private static final char CLOSE_BRACKET = ']';


    public static String checkValidAndUnpackString(String sourceString) throws UnpackingException {
        isValidString(sourceString);
        return unpacking(sourceString.toCharArray());
    }


    private static String unpacking(char[] chars) {
        int countOpenBracket = 0;
        int positionOfStartBracket = 0;
        int positionOfEndBracket;
        int numberRepetitions = 1;
        StringBuilder tempNumberStorage = new StringBuilder();
        StringBuilder resultString = new StringBuilder();

        for (int i = 0; i < chars.length; i++) {
            char symbol = chars[i];
            if (Character.isDigit(symbol) && countOpenBracket == 0) {
                tempNumberStorage.append(symbol);
            } else if (Character.isLetter(symbol)) {
                if (countOpenBracket == 0) {
                    resultString.append(symbol);
                }
            }

            switch (symbol) {
                case OPEN_BRACKET:
                    if (countOpenBracket++ == 0) {
                        positionOfStartBracket = i;
                    }
                    break;
                case CLOSE_BRACKET:
                    if (--countOpenBracket == 0) {
                        positionOfEndBracket = i;
                        if (tempNumberStorage.length() != 0)
                            numberRepetitions = Integer.parseInt(tempNumberStorage.toString());
                        resultString.append(multiplyString(numberRepetitions, unpacking(Arrays.copyOfRange(chars,
                                positionOfStartBracket + 1, positionOfEndBracket))));
                        tempNumberStorage.setLength(0);
                    }
                default:
                    break;
            }
        }

        return resultString.toString();
    }


    private static String multiplyString(int count, String sourceString) {
        StringBuilder resultString = new StringBuilder();
        for (int i = 0; i < count; i++) {
            resultString.append(sourceString);
        }
        return resultString.toString();
    }


    private static void isValidString(String sourceString) throws UnpackingException {
        if (!sourceString.matches("[\\d\\[\\]a-zA-Z]*")) {
            throw new UnpackingException(UnpackingErrorCode.WRONG_CHARACTERS,
                    "Строка должна состоять из символов: " + CORRECT_CHARSETS);
        }

        if (!sourceString.matches(".*[a-zA-Z]+.*")) {
            throw new UnpackingException(UnpackingErrorCode.WRONG_INPUT_STRING, "a-zA-Z");
        }

        Stack<Character> bracketStack = new Stack<>();

        for (int i = 0; i < sourceString.length(); i++) {
            char symbol = sourceString.charAt(i);

            switch (symbol) {
                case OPEN_BRACKET:
                    bracketStack.push(symbol);
                    break;
                case CLOSE_BRACKET:
                    if (bracketStack.isEmpty() || bracketStack.pop() != OPEN_BRACKET) {
                        throw new UnpackingException(UnpackingErrorCode.WRONG_CLOSE_BRACKET,
                                CLOSE_BRACKET + " в " + "позиции " + i);
                    }
                    break;
                default:
                    break;
            }
        }

        if (!bracketStack.isEmpty()) {
            throw new UnpackingException(UnpackingErrorCode.WRONG_OPEN_BRACKET, bracketStack.toString());
        }
    }

}
