package bowling.model;

public enum State {

    STRIKE, SPARE, MISS, GUTTER;

    public static State getStateByPins(int pins, boolean isFirst) {
        if (pins == Pins.MAX_PINS) {
            return isFirst ? STRIKE : SPARE;
        }

        if (pins == Pins.MIN_PINS) {
            return GUTTER;
        }

        return MISS;
    }

}
