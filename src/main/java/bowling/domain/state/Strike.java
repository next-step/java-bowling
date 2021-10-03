package bowling.domain.state;

import bowling.domain.PinCount;
import bowling.domain.Score;

import static bowling.domain.Indication.STRIKE;

public class Strike extends Finished {
    @Override
    public Score getScore() {
        return Score.STRIKE;
    }

    @Override
    public Score calculateBonusScore(Score score) {
        return score.plusAdditionalPinCount(PinCount.MAX_PINS);
    }

    @Override
    public String showIndication() {
        return STRIKE.toString();
    }
}
