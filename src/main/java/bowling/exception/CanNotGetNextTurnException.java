package bowling.exception;

public final class CanNotGetNextTurnException extends IllegalArgumentException {

    private static final String MESSAGE = "다음 턴을 찾을 수 없습니다.";

    public CanNotGetNextTurnException() {
        super(MESSAGE);
    }
}