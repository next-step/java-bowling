package bowling.domain.exception;

public class FinishGameException extends RuntimeException {

    public FinishGameException() {
        super("게임이 끝났습니다.");
    }
}
