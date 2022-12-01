package bowling.domain.state.normal;

import bowling.domain.PinCount;
import bowling.domain.Score;
import bowling.domain.state.Running;
import bowling.domain.state.State;
import bowling.exception.CannotCalculateException;

public class Ready extends Running {

    @Override
    public State next(PinCount pinCount) {
        if (pinCount.isTen()) {
            return new Strike();
        }

        return new FirstBowl(pinCount);
    }

    @Override
    public Score calculateBonusScore(Score beforeScore) {
        throw new CannotCalculateException();
    }
}
