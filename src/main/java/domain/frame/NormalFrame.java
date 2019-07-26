package domain.frame;

import domain.Pins;
import domain.Score;
import domain.state.open.StandBy;
import domain.state.State;

import static domain.frame.FrameIndex.MINIMUM_FRAME_INDEX;

public class NormalFrame implements Frame {

    private FrameIndex index;
    private State state;
    private Frame nextFrame;

    private NormalFrame(FrameIndex index) {
        this.index = index;
        this.state = new StandBy();
    }

    public static NormalFrame of(FrameIndex index) {
        return new NormalFrame(index);
    }

    public static Frame initFrame() {
        return of(FrameIndex.from(MINIMUM_FRAME_INDEX));
    }

    @Override
    public Frame fillFrame(Pins fallenPins) {
        state = state.update(fallenPins);
        if (state.isClosed()) {
            this.nextFrame = generateNextFrame();
            return nextFrame;
        }
        return this;
    }

    Frame generateNextFrame() {
        if (this.index.isSecondToLastIndex()) {
            return FinalFrame.of();
        }
        return NormalFrame.of(index.increment());
    }

    @Override
    public FrameIndex getIndex() {
        return index;
    }

    @Override
    public boolean isGameOver() {
        return Boolean.FALSE;
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public boolean isSameFrame(Frame targetFrame) {
        return index.isSameIndex(targetFrame);
    }

    @Override
    public Score getScore() {
        Score score = state.getScore();
        if (score.isFullyCalculated()) {
            return score;
        }
        return nextFrame.updateScore(score);
    }

    @Override
    public Score updateScore(Score score) {
        Score updatedScore = state.updateScore(score);
        if (updatedScore.isFullyCalculated()) {
            return updatedScore;
        }
        if (nextFrame == null) {
            return Score.ofUnfinished();
        }
        return nextFrame.updateScore(updatedScore);
    }

    @Override
    public FrameResult getResult() {
        return FrameResult.of(state, getScore());
    }
}
