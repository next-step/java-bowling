package bowling.domain.frame;

import bowling.domain.Score;

import java.util.Objects;

public class NormalFrame implements Frame{

    private final int round;

    private final Score score;

    private final boolean isSecondTry;

    private NormalFrame(int round, Score score, boolean isSecondTry) {
        this.round = round;
        this.score = score;
        this.isSecondTry = isSecondTry;
    }

    public static Frame start(int score) {
        return of(1, Score.first(score), false);
    }

    protected static Frame of(int round, Score score, boolean isSecondTry) {
        return new NormalFrame(round, score, isSecondTry);
    }

    @Override
    public Frame next(int score) {

        if (isSecondTry) {
            return next(round + 1, Score.first(score), false);
        }

        if (this.score.isStrike()) {
            return next(round + 1, Score.first(score), false);
        }

        return next(round, this.score.withSecond(score), true);

    }

    private static Frame next(int round, Score score, boolean isSecondTry) {
        return new NormalFrame(round, score, isSecondTry);
    }

    @Override
    public boolean isDone() {
        return round == 9 && isSecondTry;
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
