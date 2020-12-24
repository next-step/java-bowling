package bowling_step3.exception;

import static bowling.domain.NomalFrame.BOWLING_MAX_NUMBER;

public class BowlingMaxCountException extends IllegalArgumentException {
    public BowlingMaxCountException() {
        super(String.format("볼링 핀은 최대 %d개입니다.", BOWLING_MAX_NUMBER));
    }
}
