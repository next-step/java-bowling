package bowling.domain.frame;

import bowling.domain.Score;

import java.util.Objects;

public class NormalFrame {

    private static final int LAST_ROUND = 9;

    private final int round;

    private final Score score;

    private final boolean isSecondTry;

    private NormalFrame(int round, Score score, boolean isSecondTry) {
        this.round = round;
        this.score = score;
        this.isSecondTry = isSecondTry;
    }

    public static NormalFrame start(int score) {
        return of(1, Score.from(score), false);
    }

    protected static NormalFrame of(int round, Score score, boolean isSecondTry) {
        return new NormalFrame(round, score, isSecondTry);
    }

    public int nextTurnRound() {
        if (isSecondTry) {
            return round + 1;
        }

        if (score.isStrike()) {
            return round + 1;
        }

        return round;

    }

    public NormalFrame next(int score) {

        if (isSecondTry) {
            return next(round + 1, Score.from(score), false);
        }

        if (this.score.isStrike()) {
            return next(round + 1, Score.from(score), false);
        }

        return next(round, this.score.next(score), true);

    }

    private static NormalFrame next(int round, Score score, boolean isSecondTry) {
        return new NormalFrame(round, score, isSecondTry);
    }

    public boolean isLast() {
        if (round > LAST_ROUND) {
            return true;
        }

        if (round == LAST_ROUND) {
            return isDone();
        }

        return false;
    }

    private boolean isDone() {
        return score.isStrike() || isSecondTry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame normalFrame = (NormalFrame) o;
        return round == normalFrame.round && isSecondTry == normalFrame.isSecondTry && Objects.equals(score, normalFrame.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(round, score, isSecondTry);
    }
}
