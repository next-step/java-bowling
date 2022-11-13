package bowling.domain.frame.state;

import java.util.List;

import bowling.domain.BowlRecord;
import bowling.domain.frame.Score;

public class Spare extends Finished {
    private static final int SPARE_BONUS_BOWLS = 1;
    private static final String INVALID_PINS_EXCEPTION_MESSAGE = "스페어의 조건을 만족하지 않습니다.";

    private final Pins firstPins;
    private final Pins secondPins;

    public Spare(Pins firstPins, Pins secondPins) {
        validate(firstPins, secondPins);
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    @Override
    public BowlRecord createBowlRecord() {
        return new BowlRecord(List.of(firstPins, secondPins));
    }

    @Override
    public Score getScore() {
        return new Score(Pins.MAX_PINS, SPARE_BONUS_BOWLS);
    }

    @Override
    public Score calculateBonusScore(Score previousScore) {
        Score score = addBonusScore(previousScore, firstPins.getPins());
        if (score.canCalculateScore()) {
            return score;
        }

        return addBonusScore(score, secondPins.getPins());
    }

    @Override
    public boolean canBonusBowl() {
        return true;
    }

    private void validate(Pins firstPins, Pins secondPins) {
        if (!firstPins.isSpare(secondPins)) {
            throw new IllegalArgumentException(INVALID_PINS_EXCEPTION_MESSAGE);
        }
    }
}
