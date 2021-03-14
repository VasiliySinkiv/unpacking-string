package unpackingString.exception;

public enum UnpackingErrorCode {

    WRONG_CHARACTERS("Строка содержит недопустимые символы."),
    WRONG_INPUT_STRING("Строка не содержит ни одного символа:"),
    WRONG_CLOSE_BRACKET("Строка содержит неожиданную закрывающую скобку:"),
    WRONG_OPEN_BRACKET("Строка содержит незакрытые открывающиеся скобки:");

    private final String errorCode;


    UnpackingErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }


    public String getErrorCode() {
        return errorCode;
    }


    @Override
    public String toString() {
        return errorCode;
    }
}
