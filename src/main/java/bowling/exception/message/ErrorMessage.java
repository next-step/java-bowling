package bowling.exception.message;

public class ErrorMessage {

    public static final String IS_NULL_OR_EMPTY = "입력 값이 null 이거나 빈 공백 문자입니다.";
    public static final String NULL_VALUE = "입력값이 null 입니다.";

    public static final String REQUIRED_FRAME_RANGE = "프레임 번호는 1~10 까지만 가능합니다.";

    private ErrorMessage() {
    }
}
