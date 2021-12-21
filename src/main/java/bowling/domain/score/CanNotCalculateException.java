package bowling.domain.score;

import bowling.domain.exception.ServiceException;

public class CanNotCalculateException extends ServiceException {

    private static final String MESSAGE = "점수를 계산할 수 없습니다.";

    public CanNotCalculateException() {
        super(MESSAGE);
    }
}
