package bowling.domain.state;

import bowling.domain.PinCount;
import bowling.domain.Score;

public class Strike extends Finished {
    private static final String INDICATION = "X";
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
        return INDICATION;
    }
}
