package unpackingString.exception;

public class UnpackingException extends Exception {

    private UnpackingErrorCode errorCode;


    public UnpackingException(UnpackingErrorCode errorCode) {
        this.errorCode = errorCode;
    }


    public UnpackingException(UnpackingErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }


    public UnpackingErrorCode getErrorCode() {
        return errorCode;
    }
}
