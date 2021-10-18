package bowling.domain.state;

import bowling.domain.Left;
import bowling.domain.PinCount;
import bowling.domain.Score;

public class Ready extends Playing {

    @Override
    public State play(final PinCount pinCount) {
        if (pinCount.isMaxPinCount()) {
            return new Strike();
        }
        return new OncePitched(pinCount);
    }

    @Override
    public Score getScore() {
        return new Score(PinCount.MIN, Left.TWO);
    }

    @Override
    public Score calculateBonusScore(final Score score) {
        return score;
    }

    @Override
    public String showIndication() {
        return "";
    }
}
