package bowling.domain.frame.state;

import java.util.List;

import bowling.domain.BowlRecord;
import bowling.domain.frame.Score;

public class Strike extends Finished {
    private static final int STRIKE_BONUS_BOWLS = 2;

    @Override
    public BowlRecord createBowlRecord() {
        return new BowlRecord(List.of(new Pins(10)));
    }

    @Override
    public Score getScore() {
        return new Score(Pins.MAX_PINS, STRIKE_BONUS_BOWLS);
    }

    @Override
    public Score calculateBonusScore(Score previousScore) {
        if (previousScore.canCalculateScore()) {
            return previousScore;
        }

        return addBonusScore(previousScore, Pins.MAX_PINS);
    }

    @Override
    public boolean canBonusBowl() {
        return true;
    }
}
