package domain.frame;

public class GameOverException extends RuntimeException {

    public GameOverException() {
        super("게임이 종료되었습니다. 게임을 더 진행하시려면 카운터에 문의하세요.");
    }
}
