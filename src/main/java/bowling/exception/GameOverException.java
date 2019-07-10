package bowling.exception;

public class GameOverException extends IllegalStateException {

    private static final String ERROR_MESSAGE = "게임이 종료되었습니다";

    public GameOverException() {
        super(ERROR_MESSAGE);
    }
}
