package bowling.exception;

public class GameEndException extends RuntimeException {
    public GameEndException() {
        super("게임이 종료 되었습니다.");
    }
}
