package bowling.domain.frame;

import bowling.domain.FinalScore;

import java.util.Objects;

public class FinalFrame {

    private static final int ROUND = 10;

    private final FinalScore score;

    private final boolean isFirstTry;

    private final boolean isSecondTry;


    private FinalFrame(FinalScore score, boolean isFirstTry, boolean isSecondTry) {
        this.score = score;
        this.isFirstTry = isFirstTry;
        this.isSecondTry = isSecondTry;
    }

    protected static FinalFrame of(FinalScore score, boolean isFirstTry, boolean isSecondTry) {
        return new FinalFrame(score, isFirstTry, isSecondTry);
    }

    public static FinalFrame start(int score) {
        return of(FinalScore.first(score), true, false);
    }

    public FinalFrame next(int score) {
        if (isFirstTry) {
            return next(this.score.withSecond(score), true);
        }

        if (isSecondTry && this.score.isSpareOrStrike()) {
            return next(this.score.withThird(score), false);
        }

        return stop(this.score);
    }

    protected static FinalFrame next(FinalScore score, boolean isSecondTry) {
        return new FinalFrame(score, false, isSecondTry);
    }

    protected static FinalFrame stop(FinalScore score) {
        return new FinalFrame(score, false, false);
    }

    public boolean isDone() {
        if (score.isSpareOrStrike()) {
            return !isFirstTry && !isSecondTry;
        }
        return !isFirstTry && isSecondTry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalFrame that = (FinalFrame) o;
        return isSecondTry == that.isSecondTry && Objects.equals(score, that.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, isSecondTry);
    }
}
