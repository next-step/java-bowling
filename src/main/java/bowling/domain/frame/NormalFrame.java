package bowling.domain.frame;

import bowling.domain.score.NormalScore;
import bowling.domain.score.Score;

import java.util.List;
import java.util.Objects;

public class NormalFrame extends Frame {

    private static final int FIRST_IDX = 1;

    private static final int LAST = 9;

    private final int index;

    private NormalScore score;

    private NormalFrame(int index, NormalScore score, int trial) {
        super(trial);
        this.index = index;
        this.score = score;
    }

    public static NormalFrame start(int score) {
        return of(FIRST_IDX, NormalScore.first(score), FIRST_TRIAL);
    }

    protected static NormalFrame of(int index, NormalScore score, int trial) {
        return new NormalFrame(index, score, trial);
    }

    public Score score() {
        return score;
    }

    @Override
    public int next() {
        if (isNowFirstTry() && !this.score.isStrike()) {
            return index;
        }
        return index + 1;
    }

    @Override
    public List<Integer> getAllScores() {
        return score.getAll();
    }

    @Override
    public boolean isLast() {
        if (index == LAST) {
            return score.isStrike() || isNowSecondTry();
        }
        return index > LAST;
    }

    @Override
    public Frame tryNext(int score) {
        if (isNowFirstTry() && !this.score.isStrike()) {
            return trySecond(score);
        }
        return tryFirst(index + 1, score);
    }

    private NormalFrame tryFirst(int index, int score) {
        return new NormalFrame(index, NormalScore.first(score), FIRST_TRIAL);
    }

    private NormalFrame trySecond(int score) {
        this.score = this.score.second(score);
        this.trial = SECOND_TRIAL;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return index == that.index && trial == that.trial && Objects.equals(score, that.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, score, trial);
    }
}
