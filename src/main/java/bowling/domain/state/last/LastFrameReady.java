package bowling.domain.state.last;

import bowling.domain.PinCount;
import bowling.domain.Score;
import bowling.domain.state.Running;
import bowling.domain.state.State;

import bowling.exception.CannotCalculateException;

public class LastFrameReady extends Running {

    @Override
    public State next(PinCount pinCount) {
        return new LastFrameFirstBowl(pinCount);
    }

    @Override
    public Score calculateBonusScore(Score beforeScore) {
        throw new CannotCalculateException();
    }
}
