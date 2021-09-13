package bowling.domain.frame;

import bowling.domain.score.NormalScore;

import java.util.Objects;

public class NormalFrame {

    private static final int LAST_ROUND = 9;

    private final int index;

    private final NormalScore score;

    private final boolean isSecondTry;

    private NormalFrame(int index, NormalScore score, boolean isSecondTry) {
        this.index = index;
        this.score = score;
        this.isSecondTry = isSecondTry;
    }

    public static NormalFrame start(int score) {
        return of(1, NormalScore.from(score), false);
    }

    protected static NormalFrame of(int index, NormalScore score, boolean isSecondTry) {
        return new NormalFrame(index, score, isSecondTry);
    }

    public int score() {
        return score.get();
    }

    public int nextIndex() {

        if (isSecondTry) {
            return index + 1;
        }

        if (score.isStrike()) {
            return index + 1;
        }

        return index;

    }

    public NormalFrame next(int score) {

        if (isSecondTry) {
            return next(index + 1, NormalScore.from(score), false);
        }

        if (this.score.isStrike()) {
            return next(index + 1, NormalScore.from(score), false);
        }

        return next(index, this.score.next(score), true);

    }

    private static NormalFrame next(int frame, NormalScore score, boolean isSecondTry) {
        return new NormalFrame(frame, score, isSecondTry);
    }

    public boolean isLast() {
        if (index > LAST_ROUND) {
            return true;
        }

        if (index == LAST_ROUND) {
            return isDone();
        }

        return false;
    }

    public boolean isIndex(int index) {
        return this.index == index;
    }

    private boolean isDone() {
        return score.isStrike() || isSecondTry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame normalFrame = (NormalFrame) o;
        return index == normalFrame.index && isSecondTry == normalFrame.isSecondTry && Objects.equals(score, normalFrame.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, score, isSecondTry);
    }
}
