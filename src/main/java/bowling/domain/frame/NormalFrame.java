package bowling.domain.frame;

import bowling.domain.score.NormalScore;
import bowling.domain.score.Score;

import java.util.Objects;

public class NormalFrame extends BaseFrame {

    private static final int FIRST_IDX = 1;

    private static final int LAST = 9;

    private final int index;

    private Frame nextFrame;

    private NormalFrame(int index, Score score, int trial, Frame prevFrame, Frame nextFrame) {
        super(trial, score, prevFrame);
        this.index = index;
        this.nextFrame = nextFrame;
    }

    public static NormalFrame start(int score) {
        return of(FIRST_IDX, NormalScore.first(score), FIRST_TRIAL);
    }

    protected static NormalFrame of(int index, Score score, int trial) {
        return new NormalFrame(index, score, trial, null, null);
    }

    protected static NormalFrame of(int index, Score score, int trial, BaseFrame prevFrame, BaseFrame nextFrame) {
        return new NormalFrame(index, score, trial, prevFrame, nextFrame);
    }

    @Override
    public int nextIdx() {
        if (isNowFirstTry() && !isStrike()) {
            return index;
        }
        return index + 1;
    }

    @Override
    public boolean isLast() {
        if (index == LAST) {
            return isStrike() || isNowSecondTry();
        }
        return index > LAST;
    }

    @Override
    public boolean tryAll() {
        return nextIdx() != this.index;
    }

    @Override
    public Frame next() {
        return nextFrame;
    }

    @Override
    public BaseFrame bowl(int score) {
        if (isNowFirstTry() && !isStrike()) {
            return bowlSecondTry(score);
        }
        return bowlFirstTry(index + 1, score);
    }

    private BaseFrame bowlFirstTry(int index, int score) {
        if (index > LAST) {
            BaseFrame nextFrame = FinalFrame.start(score, this);
            this.nextFrame = nextFrame;
            return nextFrame;
        }
        BaseFrame nextFrame = of(index, NormalScore.first(score), FIRST_TRIAL, this, null);
        this.nextFrame = nextFrame;
        return nextFrame;
    }

    private BaseFrame bowlSecondTry(int score) {
        accumulateScore(score);
        increaseTrial();
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return index == that.index && Objects.equals(nextFrame, that.nextFrame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, nextFrame);
    }
}
