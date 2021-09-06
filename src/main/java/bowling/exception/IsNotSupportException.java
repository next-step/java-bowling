package bowling.exception;

public class IsNotSupportException extends IllegalArgumentException {
    private static final String MESSAGE = "지원 하지 않는 기능 입니다.";

    public IsNotSupportException() {
        super(MESSAGE);
    }
}
