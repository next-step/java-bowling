package bowling.exception;

public class GameOverException extends RuntimeException {

    public static final String DEFAULT_EXCEPTION_MESSAGE = "종료된 게임입니다.";

    public GameOverException() {
        super(DEFAULT_EXCEPTION_MESSAGE);
    }
}
