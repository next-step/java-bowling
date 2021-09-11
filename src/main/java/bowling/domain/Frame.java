package bowling.domain;

import java.util.Objects;

public class Frame {

    private static final int MAX_SCORE = 10;

    private final int round;

    private final int firstScore;

    private final int secondScore;

    private final boolean isSecondTry;

    public Frame(int round, int firstScore, int secondScore, boolean isSecondTry) {
        this.round = round;
        this.firstScore = firstScore;
        this.secondScore = secondScore;
        this.isSecondTry = isSecondTry;
    }

    public static Frame start(int score) {
        validateScore(score);
        return new Frame(1, score, 0, false);
    }

    private static boolean outOfRange(int score) {
        return score < 0 || score > MAX_SCORE;
    }

    public Frame nextTurn(int score) {

        validateScore(score);

        if (isSecondTry) {
            return of(round + 1, score, 0, false);
        }

        if (isStrike()) {
            return of(round + 1, score, 0, false);
        }

        return of(round, firstScore, score, true);

    }

    protected static Frame of(int round, int firstScore, int secondScore, boolean isSecondTry) {
        if (firstScore + secondScore > MAX_SCORE) {
            throw new IllegalArgumentException("한 프레임의 합계는 10점을 넘을 수 없습니다.");
        }
        return new Frame(round, firstScore, secondScore, isSecondTry);
    }

    private static void validateScore(int score) {
        if (outOfRange(score)) {
            throw new IllegalArgumentException("잘못된 점수를 입력하였습니다.");
        }
    }

    private boolean isStrike() {
        return firstScore == MAX_SCORE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame frame = (Frame) o;
        return round == frame.round && firstScore == frame.firstScore && secondScore == frame.secondScore && isSecondTry == frame.isSecondTry;
    }

    @Override
    public int hashCode() {
        return Objects.hash(round, firstScore, secondScore, isSecondTry);
    }
}
