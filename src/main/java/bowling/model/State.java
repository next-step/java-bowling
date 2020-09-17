package bowling.model;

public enum State {

    STRIKE, SPARE, MISS, GUTTER;

    public static State getStateByPins(int pins) {
        if (pins == Pins.MAX_PINS) {
            return STRIKE;
        }

        if (pins == Pins.MIN_PINS) {
            return GUTTER;
        }

        return MISS;
    }

}
