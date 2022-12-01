package bowling.domain.frame;

import bowling.domain.PinCount;
import bowling.domain.Score;
import bowling.domain.state.last.LastFrameReady;

public class LastFrame extends Frame {

    public LastFrame() {
        super(10, new LastFrameReady());
    }

    @Override
    public Score getScore() {
        return state.getScore();
    }

    @Override
    protected Score calculateBonusScore(Score score) {
        return state.calculateBonusScore(score);
    }

    @Override
    public Frame bowl(PinCount pinCount) {
        state = state.next(pinCount);
        return this;
    }
}
