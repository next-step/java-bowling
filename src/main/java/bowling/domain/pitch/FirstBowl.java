package bowling.domain.pitch;

import bowling.domain.exception.OutOfRangeArgumentException;

class FirstBowl implements State {
    private static final String OUT_OF_RANGE_ERROR_MESSAGE =
            "잘못된 입력값 입니다.";
    private Pin pin;

    public FirstBowl(Pin pin) {
        this.pin = pin;
    }

    @Override public State bowl(Pin pin) {
        if (!this.pin.isInRangeAfterAdd(pin)) {
            throw new OutOfRangeArgumentException(OUT_OF_RANGE_ERROR_MESSAGE);
        }

        if (this.pin.add(pin).isMax()) {
            return new Spare();
        }

        return new Miss();
    }
}
