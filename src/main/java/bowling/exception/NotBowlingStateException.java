package bowling.exception;

public class NotBowlingStateException extends IllegalStateException {

    private static final String MESSAGE = "이미 완료 된 상태 입니다.";

    public NotBowlingStateException() {
        super(MESSAGE);
    }

}
