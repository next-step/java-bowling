package bowling.domain.frame.exception;

public class UnableCreateFrameException extends RuntimeException {

    private static final String EXCEPTION_MESSAGE = "현재 프레임이 마지막입니다. 더 이상 게임을 진행할 수 없습니다.";

    public UnableCreateFrameException() {
        super(EXCEPTION_MESSAGE);
    }
}
