package bowling.exception;

public class EndedFrameException extends RuntimeException {
    private static final String MESSAGE1 = "ERROR] %s라운드는 이미 종료되었습니다.";
    private static final String MESSAGE2 = "ERROR] 해당 라운드는 이미 종료되었습니다.";

    public EndedFrameException(int round) {
        super(String.format(MESSAGE1, round));
    }

    public EndedFrameException() {
        super(String.format(MESSAGE2));
    }
}
