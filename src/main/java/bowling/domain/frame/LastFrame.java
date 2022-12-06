package bowling.domain.frame;

import bowling.domain.PinCount;
import bowling.domain.Result;
import bowling.domain.Score;
import bowling.domain.state.last.LastFrameReady;
import bowling.exception.CannotCalculateException;

import java.util.Optional;

public class LastFrame extends Frame {

    public LastFrame() {
        super(10, new LastFrameReady());
    }

    @Override
    public Score getScore() {
        return score.orElseGet(() -> {
            Score newScore = state.getScore();
            score = Optional.of(newScore);
            return newScore;
        });
    }

    @Override
    protected Score calculateBonusScore(Score beforeScore) {
        return state.calculateBonusScore(beforeScore);
    }

    @Override
    public Frame bowl(PinCount pinCount) {
        state = state.next(pinCount);
        return this;
    }
}
