package bowling.exception;

public class NotCreateFrameException extends Exception {

    private static final String MESSAGE = "다음 프레임을 생성할 수 없습니다. 현재 프레임: %d";

    public NotCreateFrameException(int frameNo) {
        super(String.format(MESSAGE, frameNo));
    }
}
