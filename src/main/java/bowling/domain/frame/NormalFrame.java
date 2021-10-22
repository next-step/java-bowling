package bowling.domain.frame;

import bowling.domain.score.Pin;
import bowling.domain.score.Score;
import bowling.domain.state.State;
import bowling.domain.state.running.Ready;
import bowling.exception.frame.NextFrameNotFoundException;
import bowling.exception.state.RunningCreateScoreException;
import bowling.exception.state.StateCannotCalculateScoreException;

public class NormalFrame extends AbstractFrame {

    private Frame nextFrame;
    private State state;

    private NormalFrame(int round, Frame nextFrame, State state) {
        super(round);
        this.nextFrame = nextFrame;
        this.state = state;
    }

    static Frame from(int round, Frame nextFrame, State state) {
        return new NormalFrame(round, nextFrame, state);
    }

    public static Frame createFirstFrame() {
        return new NormalFrame(FIRST_ROUND, null, new Ready());
    }

    public Frame nextFrame() {
        return nextFrame;
    }

    @Override
    public Frame bowling(Pin pin) {
        state = state.bowl(pin);
        if (state.isFinished()) {
            return createNextFrame();
        }
        return this;
    }

    private Frame createNextFrame() {
        if (round() + 1 == FINAL_ROUND) {
            return FinalFrame.createFinalFrame();
        }
        return new NormalFrame(round() + 1, null, new Ready());
    }

    @Override
    public FrameResult createFrameResult() {
        try {
            return FrameResult.of(score().score(), state.desc());
        } catch (NextFrameNotFoundException | StateCannotCalculateScoreException | RunningCreateScoreException e) {
            return FrameResult.createFrameResultByNoCaculatedScore(state.desc());
        }
    }

    Score score() {
        Score score = state.createScore();
        if (score.canCalculateScore()) {
            return score;
        }
        return nextFrame().calculateAdditionalScore(score);
    }

    @Override
    public Score calculateAdditionalScore(Score beforeScore) {
        Score score = state.calculateAdditionalScore(beforeScore);
        if (score.canCalculateScore()) {
            return score;
        }
        return nextFrame().calculateAdditionalScore(score);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}
