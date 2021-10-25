package bowling.model;

public class GameOverException extends RuntimeException {
    public GameOverException() {
        super("게임이 종료되었습니다.");
    }
}
