package bowling.domain.frame;

import bowling.domain.exception.ServiceException;

public class UnFinishedFrameException extends ServiceException {

    private static final String MESSAGE = "현재 프레임이 종료되지 않았습니다.";

    public UnFinishedFrameException() {
        super(MESSAGE);
    }
}
