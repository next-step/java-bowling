package bowling.domain.exception;

public class IncorrectNumberOfPinsException extends RuntimeException {

    public IncorrectNumberOfPinsException() {
        super("볼링 게임의 핀수 개수는 10개를 초과할 수 없습니다.");
    }
}
