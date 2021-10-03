package bowling.domain.state;

import bowling.domain.PinCount;
import bowling.domain.Score;

import static bowling.domain.Indication.*;

public class Spare extends Finished {
    private final PinCount first;

    private final PinCount second;

    public Spare(final PinCount first, final PinCount second) {
        validatePinCountSum(first, second);
        this.first = first;
        this.second = second;
    }

    private void validatePinCountSum(PinCount first, PinCount second) {
        if (!first.isSpare(second)) {
            throw new IllegalArgumentException("Spare는 PinCount 합이 10이어야합니다. first : " + first.getValue() + ", second : " + second.getValue());
        }
    }

    @Override
    public Score getScore() {
        return Score.SPARE;
    }

    @Override
    public Score calculateBonusScore(Score beforeScore) {
        Score score = beforeScore.plusAdditionalPinCount(first);
        if (!score.canCalculate()) {
            return score.plusAdditionalPinCount(second);
        }
        return score;
    }

    @Override
    public String showIndication() {
        return first.showIndication() + SEPARATOR + SPARE;
    }
}
