package bowling.exception;

public class EndedFrameException extends RuntimeException {
    private static final String MESSAGE = "ERROR] %s라운드는 이미 종료되었습니다.";

    public EndedFrameException(int round) {
        super(String.format(MESSAGE, round));
    }
}
