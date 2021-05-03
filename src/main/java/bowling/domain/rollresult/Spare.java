package bowling.domain.rollresult;

import java.util.Objects;

public class Spare implements RollResultType {
    private static final String INVALID_FIRST_SCORE = "스페어의 첫번째 값은 10보다 작아야합니다.";
    private static final String INVALID_SECOND_SCORE = "스페어의 두번째 값은 10-첫번째값보다 커야합니다.";

    private RollResultType firstHit;
    private OneHit secondHit;

    private Spare(RollResultType firstHit) {
        this(firstHit, OneHit.ofOne(DEFAULT_MAX_SCORE - firstHit.eval()));
    }

    private Spare(RollResultType firstHit, OneHit secondHit) {
        this.firstHit = firstHit;
        this.secondHit = secondHit;
    }

    public static Spare of(int firstScore) {
        validFirst(firstScore);
        return new Spare(OneHit.of(firstScore));
    }

    public static Spare of(RollResultType firstHit) {
        validFirst(firstHit.eval());
        return new Spare(firstHit);
    }

    public static Spare of(RollResultType firstHit, OneHit secondHit) {
        valid(firstHit.eval(), secondHit.eval());
        return new Spare(firstHit, secondHit);
    }

    private static void valid(int firstScore, int secondScore) {
        validFirst(firstScore);
        if(secondScore < DEFAULT_MAX_SCORE - firstScore) {
            throw new IllegalArgumentException(INVALID_SECOND_SCORE);
        }
    }

    private static void validFirst(int firstScore) {
        if(firstScore >= DEFAULT_MAX_SCORE) {
            throw new IllegalArgumentException(INVALID_FIRST_SCORE);
        }
    }

    @Override
    public boolean isStrike() {
        return false;
    }

    @Override
    public boolean isSpare() {
        return true;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public int eval() {
        return firstHit.eval() + secondHit.eval();
    }

    @Override
    public RollResultType next(int nextScore) {
        return of(firstHit, secondHit.add(nextScore));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spare spare = (Spare) o;
        return Objects.equals(firstHit, spare.firstHit) && Objects.equals(secondHit, spare.secondHit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstHit, secondHit);
    }

    @Override
    public String toString() {
        return firstHit.toString() + "|/";
    }
}
