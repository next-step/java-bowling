package bowling.domain.frame;

import bowling.domain.score.FinalScore;

import java.util.Objects;

public class FinalFrame {

    private final FinalScore score;

    private final boolean isFirstTry;

    private final boolean isSecondTry;

    private final boolean isThirdAvailable;
    private final boolean isDone;


    private FinalFrame(FinalScore score, boolean isFirstTry, boolean isSecondTry, boolean isThirdAvailable, boolean isDone) {
        this.score = score;
        this.isFirstTry = isFirstTry;
        this.isSecondTry = isSecondTry;
        this.isThirdAvailable = isThirdAvailable;
        this.isDone = isDone;
    }

    protected static FinalFrame of(FinalScore score, boolean isFirstTry, boolean isSecondTry, boolean isThirdAvailable, boolean isDone) {
        return new FinalFrame(score, isFirstTry, isSecondTry, isThirdAvailable, isDone);
    }

    public static FinalFrame start(int score) {
        return of(FinalScore.from(score), true, false, false, false);
    }

    public FinalFrame next(int score) {
        if (isFirstTry) {
            return secondTry(score);
        }

        if (isSecondTry && isThirdAvailable) {
            return thirdTry(this.score.next(score));
        }

        return stop(this.score);
    }

    private FinalFrame secondTry(int score) {

        if (this.score.isStrike()) {
            return secondTry(this.score.next(score), true, false);
        }

        if (this.score.isSpareWhenAdd(score)) {
            return secondTry(this.score.next(score), true, false);
        }

        return secondTry(this.score.next(score), false, true);
    }

    protected static FinalFrame secondTry(FinalScore score, boolean isThirdAvailable, boolean isDone) {
        return new FinalFrame(score, false, true, isThirdAvailable, isDone);
    }

    protected static FinalFrame thirdTry(FinalScore score) {
        return new FinalFrame(score, false, false, false, true);
    }

    protected static FinalFrame stop(FinalScore score) {
        return new FinalFrame(score, false, false, false, true);
    }

    public boolean isDone() {
        return isDone;
    }

    public int score() {
        return score.get();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalFrame that = (FinalFrame) o;
        return isFirstTry == that.isFirstTry && isSecondTry == that.isSecondTry && isDone == that.isDone && Objects.equals(score, that.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, isFirstTry, isSecondTry, isDone);
    }
}
