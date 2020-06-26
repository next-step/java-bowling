package bowling.domain;

public class IncorrectPinException extends RuntimeException {
    public static final String MESSAGE = "핀의 갯수는 0보다 작거나 10보다 클 수 없습니다.";

    public IncorrectPinException() {
        super(MESSAGE);
    }
}
