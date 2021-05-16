package bowling.exception;

public enum ErrorCode {
    INVALID_PIN("P001", "Pin should be number between 0~10"),
    INVALID_SECOND_PIN("P002", "Second Pin is either not in range of 0~10 or exceed 10 when added with first pin"),

    INVALID_BOWL("PI001", "Cannot bowl to fully occupied pins"),

    BONUS_LEFT("B001", "Still need to add bonus points in frame"),

    INVALID_SCORE("S001", "score added to single frame should be between 0~10"),
    INVALID_SCORE_ADDITION("S002", "Cannot add score after score is fixed");

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
