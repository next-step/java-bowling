package bowling.domain.frame;

import bowling.domain.exception.ServiceException;

public class NoNextFrameException extends ServiceException {

    private static final String MESSAGE = "다음 프레임이 없습니다.";

    public NoNextFrameException() {
        super(MESSAGE);
    }
}
