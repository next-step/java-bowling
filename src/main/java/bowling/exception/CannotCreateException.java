package bowling.exception;

public class CannotCreateException extends Exception{
    public static final String SCORE_EXCEPTION = "0 에서 10 사이의 숫자만 사용 가능합니다.";

    public CannotCreateException(String message) {
        super(message);
    }
}
