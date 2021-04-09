package bowling.exception;

public enum ErrorCode {

    INVALID_NAME("N001", "Name should be 3 English letters");

    private String code;
    private String message;

    ErrorCode(String code, String message){
        this.code = code;
        this.message = message;
    }
}
