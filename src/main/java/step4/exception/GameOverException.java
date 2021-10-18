package step4.exception;

public class GameOverException extends RuntimeException {
    private static String GAME_OVER = "게임이 종료되었습니다.";

    public GameOverException() {
        super(GAME_OVER);
    }
}
