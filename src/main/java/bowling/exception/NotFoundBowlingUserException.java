package bowling.exception;

public class NotFoundBowlingUserException extends IllegalStateException {
    private static final String ERROR_MESSAGE = "알맞은 볼링 유저를 찾을 수 없습니다.";

    public NotFoundBowlingUserException() {
        super(ERROR_MESSAGE);
    }

}
