package bowling.domain;

import java.util.Objects;

public class Score {
    public static final int MINIMUM_VALUE = 0;
    public static final int MAXIMUM_VALUE = 10;
    public static final int NO_TRY = 0;
    public static final int ONE_TRY = 1;
    public static final int TWO_TRIES = 2;
    public static final int THREE_TRIES = 3;
    public static final Score UNSCORED = new Score(-1,-1);

    private final int fallenPins;
    private final int triesLeft;

    public Score(int fallenPins, int triesLeft) {
        this.fallenPins = fallenPins;
        this.triesLeft = triesLeft;
    }

    public boolean hasNoTryLeft() {
        return triesLeft == NO_TRY;
    }

    public boolean hasOneTryLeft() {
        return triesLeft == ONE_TRY;
    }

    public boolean hasTwoTriesLeft() {
        return triesLeft == TWO_TRIES;
    }

    public int getFallenPins() {
        return fallenPins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Score)) return false;
        Score score = (Score) o;
        return fallenPins == score.fallenPins &&
                triesLeft == score.triesLeft;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fallenPins, triesLeft);
    }
}
