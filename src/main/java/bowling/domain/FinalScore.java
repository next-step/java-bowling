package bowling.domain;

import java.util.Objects;

public class FinalScore extends Score {

    private final int third;

    protected FinalScore(int first, int second, int third) {
        super(first, second);
        this.third = third;
    }

    public static FinalScore first(int score) {
        validateScore(score);
        return new FinalScore(score, 0, 0);
    }

    public FinalScore withSecond(int score) {
        validateScore(score);
        validateComibnedScores(score);
        return new FinalScore(getFirst(), score, 0);
    }

    public FinalScore withThird(int score) {
        validateScore(score);
        return new FinalScore(getFirst(), getSecond(), score);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FinalScore that = (FinalScore) o;
        return third == that.third;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), third);
    }
}
