package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;
import bowling.exception.CannotCalculateException;

abstract class Running implements State {

    protected static final String BOWLING_STATUS_GUTTER = "-";

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public Score getScore() {
        throw new CannotCalculateException();
    }

    protected String ifCountOfPinsZeroTransGutter(int pins) {
        if (pins == Pin.MINIMUM_PIN_COUNT) {
            return BOWLING_STATUS_GUTTER;
        }
        return String.valueOf(pins);
    }
}
