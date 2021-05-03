package bowling.rollresult;

import java.util.Objects;

public class Spare implements RollResultType {
    private static final String INVALID_FIRST_SCORE = "스페어의 첫번째 값은 10보다 작아야합니다.";
    private static final String INVALID_SECOND_SCORE = "스페어의 두번째 값은 10-첫번째값보다 커야합니다.";

    private final int firstScore;
    private final int secondScore;

    private Spare(int firstScore) {
        this.firstScore = firstScore;
        secondScore = DEFAULT_MAX_SCORE - firstScore;
    }

    private Spare(int firstScore, int secondScore) {
        this.firstScore = firstScore;
        this.secondScore = secondScore;
    }

    public static Spare of(int firstScore) {
        validFirst(firstScore);
        return new Spare(firstScore);
    }

    public static Spare of(int firstScore, int secondScore) {
        valid(firstScore, secondScore);
        return new Spare(firstScore, secondScore);
    }

    private static void validFirst(int firstScore) {
        if(firstScore >= DEFAULT_MAX_SCORE) {
            throw new IllegalArgumentException(INVALID_FIRST_SCORE);
        }
    }

    private static void valid(int firstScore, int secondScore) {
        validFirst(firstScore);
        if (secondScore < (DEFAULT_MAX_SCORE - firstScore)) {
            throw new IllegalArgumentException(INVALID_SECOND_SCORE);
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
        return firstScore + secondScore;
    }

    @Override
    public RollResultType next(int nextScore) {
        return of(firstScore, secondScore + nextScore);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spare spare = (Spare) o;
        return firstScore == spare.firstScore && secondScore == spare.secondScore;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstScore, secondScore);
    }
}
