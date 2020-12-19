package bowling.exception;

public class NotHasTurnException extends IllegalArgumentException{

    public static final String NOT_HAS_TURN = "던지는 횟수를 초과하셨습니다.";

    public NotHasTurnException() {
        super(NOT_HAS_TURN);
    }
}
