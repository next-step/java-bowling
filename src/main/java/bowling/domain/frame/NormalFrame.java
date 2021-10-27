package bowling.domain.frame;

import bowling.domain.score.Pin;
import bowling.domain.score.Score;
import bowling.domain.state.State;
import bowling.domain.state.running.Ready;
import bowling.exception.frame.NextFrameNotFoundException;
import bowling.exception.state.RunningCreateScoreException;
import bowling.exception.state.StateCannotCalculateScoreException;
import java.util.Optional;

public class NormalFrame extends AbstractFrame {

    private Frame nextFrame;

    private NormalFrame(int round, Frame nextFrame, State state) {
        super(round, state);
        this.nextFrame = nextFrame;
    }

    static Frame from(int round, Frame nextFrame, State state) {
        return new NormalFrame(round, nextFrame, state);
    }

    public static Frame createFirstFrame() {
        return new NormalFrame(FIRST_ROUND, null, new Ready());
    }

    @Override
    public Optional<Frame> nextFrame() {
        return Optional.ofNullable(nextFrame);
    }

    @Override
    public Frame bowling(Pin pin) {
        stateBowling(pin);
        if (stateIsFinished()) {
            return createNextFrame();
        }
        return this;
    }

    private Frame createNextFrame() {
        if (round() + 1 == FINAL_ROUND) {
            return nextFrame = FinalFrame.createFinalFrame();
        }
        return nextFrame = new NormalFrame(round() + 1, null, new Ready());
    }

    @Override
    public FrameResult createFrameResult() {
        try {
            return FrameResult.ofScoreAndDesc(score().score(), desc());
        } catch (NextFrameNotFoundException | StateCannotCalculateScoreException | RunningCreateScoreException e) {
            return FrameResult.createFrameResultByNoCaculatedScore(desc());
        }
    }

    Score score() {
        Score score = createScore();
        if (score.canCalculateScore()) {
            return score;
        }
        return calculateAdditionalScoreByNextFrame(score);
    }

    private Score calculateAdditionalScoreByNextFrame(Score score) {
        return nextFrame()
            .map(frame -> frame.calculateAdditionalScore(score))
            .orElseThrow(NextFrameNotFoundException::new);
    }

    @Override
    public Score calculateAdditionalScore(Score beforeScore) {
        Score score = stateCalculateAdditionalScore(beforeScore);
        if (score.canCalculateScore()) {
            return score;
        }
        return calculateAdditionalScoreByNextFrame(score);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean frameIsFinished() {
        return stateIsFinished();
    }

}
