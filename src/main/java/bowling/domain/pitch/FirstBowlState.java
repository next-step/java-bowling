package bowling.domain.pitch;

import bowling.domain.exception.OutOfRangeArgumentException;

class FirstBowlState implements State {
    private static final String OUT_OF_RANGE_ERROR_MESSAGE =
            "잘못된 입력값 입니다.";
    private Pin pin;

    public FirstBowlState(Pin pin) {
        this.pin = pin;
    }

    @Override public State bowl(Pin pin) {
        if (!this.pin.isInRangeAfterAdd(pin)) {
            throw new OutOfRangeArgumentException(OUT_OF_RANGE_ERROR_MESSAGE);
        }

        if (this.pin.add(pin).isMax()) {
            return new SpareState();
        }

        return new MissState();
    }
}
