package bowling.domain.score;

import bowling.domain.exception.ServiceException;

public class IllegalScoreException extends ServiceException {

    public IllegalScoreException(String message) {
        super(message);
    }
}
