package bowling.exception;

public enum ErrorCode {

    INVALID_NAME("N001", "Name should be 3 English letters"),

    INVALID_POINT("P001", "Point should be number between 0~10"),
    INVALID_POINT_SUM("P002", "Points in a frame cannot exceed 10 pin points");

    private String code;
    private String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
