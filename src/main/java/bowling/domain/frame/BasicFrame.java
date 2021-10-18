package bowling.domain.frame;

import bowling.domain.FrameResult;
import bowling.domain.PinCount;
import bowling.domain.Score;
import bowling.domain.state.State;
import bowling.domain.state.StateFactory;

import java.util.Objects;

public class BasicFrame extends AbstractFrame {

    private State state;

    private Frame next;

    public BasicFrame(final FrameNumber frameNumber) {
        super(frameNumber);
        this.state = StateFactory.ready();
    }

    @Override
    public Frame play(final PinCount pinCount) {
        state = this.state.play(pinCount);
        if (state.isFinished()) {
            next = createFrame();
            return next;
        }
        return this;
    }

    private Frame createFrame() {
        FrameNumber nextFrameNumber = getFrameNumber().next();
        if (nextFrameNumber.isFinalNumber()) {
            return new FinalFrame(nextFrameNumber);
        }
        return new BasicFrame(nextFrameNumber);
    }

    @Override
    public Score calculateScore() {
        Score score = state.getScore();
        if (score.canCalculate() || hasNotNext()) {
            return score;
        }
        return next.plusBonusScore(score);
    }

    @Override
    public Score plusBonusScore(final Score beforeScore) {
        Score score = state.calculateBonusScore(beforeScore);
        if (score.canCalculate() || hasNotNext()) {
            return score;
        }
        return next.plusBonusScore(score);
    }

    private boolean hasNotNext() {
        return Objects.isNull(next);
    }

    @Override
    public FrameResult makeResult() {
        return new FrameResult(state.showIndication(), calculateScore());
    }
}
