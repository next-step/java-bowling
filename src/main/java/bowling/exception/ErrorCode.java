package bowling.exception;

public enum ErrorCode {
    INVALID_POINT("P001", "Point should be number between 0~10");

    private String code;
    private String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }
}
