package bowling.exception;

public class CannotCreateException extends Exception{
    public static final String SCORE_ERROR_MSG = "0 에서 10 사이의 숫자만 사용 가능합니다.";
    public static final String SECOND_SCORE_ERROR_MSG = "스코어의 합이 10이하이여야 합니다.";

    public CannotCreateException(String message) {
        super(message);
    }
}
