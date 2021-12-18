package bowling.domain.bowl;

import bowling.domain.exception.ServiceException;

public class CanNotPitchException extends ServiceException {

    private static final String DEFAULT_MESSAGE = "투구할 수 없습니다.";

    public CanNotPitchException(String message) {
        super(message);
    }

    public CanNotPitchException() {
        super(DEFAULT_MESSAGE);
    }
}
