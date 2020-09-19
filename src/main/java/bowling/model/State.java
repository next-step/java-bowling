package bowling.model;

public enum State {

    STRIKE(2), SPARE(1), MISS(0), GUTTER(0);

    final private int countOfBonusScore;

    State(int countOfBonusScore) {
        this.countOfBonusScore = countOfBonusScore;
    }

    public static State getStateByPins(int pins, boolean isFirst) {
        if (pins == Pins.MAX_PINS) {
            return isFirst ? STRIKE : SPARE;
        }

        if (pins == Pins.MIN_PINS) {
            return GUTTER;
        }

        return MISS;
    }

    public int getCountOfBonusScore() {
        return countOfBonusScore;
    }

}
