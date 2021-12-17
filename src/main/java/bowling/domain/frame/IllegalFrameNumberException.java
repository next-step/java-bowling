package bowling.domain.frame;

import bowling.domain.exception.ServiceException;

public class IllegalFrameNumberException extends ServiceException {

    private static final String MESSAGE = "1프레임 부터 10프레임 까지만 가능합니다.";

    public IllegalFrameNumberException() {
        super(MESSAGE);
    }
}
