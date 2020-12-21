package bowling.exception;

import static bowling.domain.BowlingKnockDown.MAX_NUMBER;

public class BowlingMaxCountException extends IllegalArgumentException {
    public BowlingMaxCountException() {
        super(String.format("볼링 핀은 최대 %d개입니다.", MAX_NUMBER));
    }
}
