package bowling.domain.state;

import bowling.domain.Score;

import java.util.Objects;

public class Spare extends State {
    private static final String INVALID_FIRST_SCORE = "스페어의 첫번째 값은 10보다 작아야합니다.";
    private static final String INVALID_SECOND_SCORE = "스페어의 두번째 값은 10-첫번째값보다 커야합니다.";

    private final State firstHit;
    private final State secondHit;

    private Spare(State firstHit) {
        this(firstHit, OneHit.ofOne(Score.ofSpare(firstHit.eval())));
    }

    private Spare(State firstHit, OneHit secondHit) {
        this.firstHit = firstHit;
        this.secondHit = secondHit;
    }

    private Spare(State firstHit, State secondHit) {
        this.firstHit = firstHit;
        this.secondHit = secondHit;
    }

    public static Spare of(int firstScore) {
        validFirst(firstScore);
        return new Spare(OneHit.of(firstScore));
    }

    public static Spare of(State firstHit) {
        validFirst(firstHit.eval());
        return new Spare(firstHit);
    }

    public static Spare of(State firstHit, OneHit secondHit) {
        valid(firstHit.eval(), secondHit.eval());
        return new Spare(firstHit, secondHit);
    }

    public static Spare of(State firstHit, State secondHit) {
        valid(firstHit.eval(), secondHit.eval());
        return new Spare(firstHit, secondHit);
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
    public boolean canAccumulate() {
        return !secondHit.eval().isFinished();
    }

    @Override
    public Score eval() {
        return firstHit.eval().calculate(secondHit.eval());
    }

    @Override
    public State next(int nextScore) {
        return of(firstHit, OneHit.of(secondHit.eval().add(nextScore)));
    }

    private static void valid(Score firstScore, Score secondScore) {
        validFirst(firstScore);
        if(!firstScore.calculate(secondScore).isClear()) {
            throw new IllegalArgumentException(INVALID_SECOND_SCORE);
        }
    }

    private static void validFirst(Score firstScore) {
        if(firstScore.isStrike()) {
            throw new IllegalArgumentException(INVALID_FIRST_SCORE);
        }
    }

    private static void validFirst(int firstScore) {
        validFirst(Score.of(firstScore));
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
