package bowling.domain.frame;

import bowling.domain.Pins;
import bowling.domain.state.ThrowingState;
import bowling.domain.state.running.Ready;
import bowling.exception.CannotScoreCalculateException;

import java.util.Objects;

public class NormalFrame implements Frame {

    private final FrameIndex index;
    private ThrowingState state;
    private Frame nextFrame;

    private NormalFrame(FrameIndex index) {
        this.index = index;
        this.state = Ready.create();
    }

    static Frame first() {
        return new NormalFrame(FrameIndex.first());
    }

    @Override
    public Frame bowl(Pins pins) {
        this.state = state.bowl(pins);
        if (state.isEnd()) {
            this.nextFrame = createNextFrame();
            return this.nextFrame;
        }
        return this;
    }

    @Override
    public int getIndex() {
        return index.getIndex();
    }

    @Override
    public boolean isEnd() {
        return state.isEnd();
    }

    @Override
    public String symbol() {
        return state.symbol();
    }

    @Override
    public int score() {
        try {
            return getScore().getScore();
        } catch (CannotScoreCalculateException e) {
            return Score.UN_SCORE_STATE;
        }
    }

    private Score getScore() throws CannotScoreCalculateException {
        Score score = state.getScore();
        if (score.isCalculatorScore()) {
            return score;
        }
        return nextFrame.calculateAdditionalScore(score);
    }

    @Override
    public Score calculateAdditionalScore(Score beforeScore) throws CannotScoreCalculateException {
        Score score = state.calculateAdditionalScore(beforeScore);
        if (score.isCalculatorScore()) {
            return score;
        }
        return nextFrame.calculateAdditionalScore(score);
    }

    private Frame createNextFrame() {
        if (isLastBeforeFrame()) {
            return LastFrame.first();
        }
        return new NormalFrame(index.next());
    }

    private boolean isLastBeforeFrame() {
        return state.isEnd() && index.lastBeforeIndex();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return Objects.equals(getIndex(), that.getIndex()) && Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIndex(), state);
    }

    @Override
    public String toString() {
        return "NormalFrame{" +
                "index=" + index +
                ", state=" + state +
                '}';
    }
}
