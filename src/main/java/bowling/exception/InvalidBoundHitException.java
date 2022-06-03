package bowling.exception;

import bowling.domain.Hit;

public class InvalidBoundHitException extends IllegalArgumentException {

    private static final String MESSAGE = "Hit 는 %d ~ %d 사이의 값만 가능합니다.";

    public InvalidBoundHitException() {
        super(String.format(MESSAGE, Hit.MIN_NUMBER, Hit.MAX_NUMBER));
    }
}
