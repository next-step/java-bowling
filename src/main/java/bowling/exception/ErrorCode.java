package bowling.exception;

public enum ErrorCode {
    INVALID_PIN("P001", "Pin should be number between 0~10"),
    INVALID_SECOND_PIN("P002", "Second Pin is either not in range of 0~10 or exceed 10 when added with first pin");

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
