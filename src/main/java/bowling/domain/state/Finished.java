package bowling.domain.state;

import bowling.domain.Pin;

abstract class Finished implements State {

    protected static final String BOWLING_STATUS_GUTTER = "-";

    @Override
    public State bowl(Pin pin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isFinish() {
        return true;
    }

    protected String ifCountOfPinsZeroTransGutter(int pins) {
        if (pins == Pin.MINIMUM_PIN_COUNT) {
            return BOWLING_STATUS_GUTTER;
        }
        return String.valueOf(pins);
    }

}
