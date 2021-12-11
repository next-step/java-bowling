package bowling.domain.pin;

import bowling.domain.exception.ServiceException;

public class IllegalRangeOfHitCountException extends ServiceException {

    private static final String MESSAGE = "넘어 뜨린 핀의 개수는 0개 이상 10개 이하여야 합니다.";

    public IllegalRangeOfHitCountException() {
        super(MESSAGE);
    }
}
