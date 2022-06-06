package bowling.exception;

public class NotSupportMethodException extends RuntimeException {
    private static final String MESSAGE = "지원하지 않는 메소드입니다.";

    public NotSupportMethodException() {
        super(MESSAGE);
    }
}
