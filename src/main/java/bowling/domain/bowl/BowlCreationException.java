package bowling.domain.bowl;

import bowling.domain.exception.ServiceException;

public class BowlCreationException extends ServiceException {
    public BowlCreationException(String message) {
        super(message);
    }
}
