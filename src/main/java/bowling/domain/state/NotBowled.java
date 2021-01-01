package bowling.domain.state;

import bowling.domain.Score;

import static bowling.common.SymbolConstants.NOT_THROWN;
import static bowling.domain.Score.*;

public class NotBowled implements Calculated{
    private NotBowled() {}

    public static NotBowled init() {
        return new NotBowled();
    }

    @Override
    public boolean isOver() {
        return false;
    }

    @Override
    public State bowl(int pins) {
        if(pins == MAXIMUM_VALUE) {
            return new Strike();
        }

        return BowledOnce.of(pins);
    }

    @Override
    public Score calculateScore() {
        return UNSCORED;
    }

    @Override
    public Score addBonusScore(Score prevScore) {
        return UNSCORED;
    }

    @Override
    public int getNumberOfTries() {
        return NO_TRY;
    }

    @Override
    public String display() {
        return NOT_THROWN;
    }
}
