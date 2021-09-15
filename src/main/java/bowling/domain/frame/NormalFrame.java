package bowling.domain.frame;

import bowling.domain.score.NormalScore;

import java.util.Objects;

public class NormalFrame extends Frame {

    private static final int LAST_ROUND = 9;

    private final int index;

    private NormalScore score;

    private NormalFrame(int index, NormalScore score, int trial) {
        super(trial);
        this.index = index;
        this.score = score;
    }

    public static NormalFrame init() {
        return of(0, NormalScore.start(), 1);
    }

    protected static NormalFrame of(int index, NormalScore score, int trial) {
        return new NormalFrame(index, score, trial);
    }

    public NormalScore score() {
        return score;
    }

    public int nextIndex() {

        if (isSecondTry()) {
            return index + 2;
        }

        if (score.isStrike()) {
            return index + 2;
        }

        return index + 1;

    }

    public NormalFrame tryFirst(int score) {
        return of(index + 1, NormalScore.first(score), 1);
    }


    public NormalFrame trySecond(int score) {
        this.score = this.score.second(score);
        this.trial = 2;
        return this;
    }

    @Override
    public boolean isLast() {
        if (index > LAST_ROUND) {
            return true;
        }

        if (index == LAST_ROUND) {
            return isNowFrameDone();
        }

        return false;
    }

    public boolean isFrame(int frame) {
        return this.index == frame;
    }

    public boolean isNowFrameDone() {
        return score.isStrike() || isSecondTry();
    }

    private boolean isSecondTry() {
        return trial == 2;
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
