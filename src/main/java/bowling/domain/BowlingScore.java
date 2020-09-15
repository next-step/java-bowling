package bowling.domain;

public class BowlingScore {
    private static final int LOWER_BOUND = 0;
    private static final int UPPER_BOUND = 10;
    private static final String UNDER_LOWER_BOUND_EXCEPTION_MESSAGE = "점수는 0이상의 값이여야 합니다.";
    private static final String EXCEED_UPPER_BOUND_EXCEPTION_MESSAGE = "점수는 10이하의 값이여야 합니다.";
    private static final String GUTTER_SYMBOL = "-";
    private static final BowlingScore EMPTY_SCORE = new EmptyBowlingScore();

    private int score;

    public static BowlingScore emptyScore() {
        return EMPTY_SCORE;
    }

    private BowlingScore() {

    }

    public BowlingScore(int score) {
        validateScore(score);
        this.score = score;
    }

    private void validateScore(int score) {
        validateLowerBound(score);
        validateUpperBound(score);
    }

    private void validateLowerBound(int score) {
        if (score < LOWER_BOUND) {
            throw new IllegalArgumentException(UNDER_LOWER_BOUND_EXCEPTION_MESSAGE);
        }
    }

    private void validateUpperBound(int score) {
        if (score > UPPER_BOUND) {
            throw new IllegalArgumentException(EXCEED_UPPER_BOUND_EXCEPTION_MESSAGE);
        }
    }

    // contain validate
    public BowlingScore add(BowlingScore bowlingScore) {
        return new BowlingScore(this.score + bowlingScore.score);
    }

    public static boolean isExceedLimitToAdd(BowlingScore target, BowlingScore value) {
        return target.score + value.score > UPPER_BOUND;
    }

    public boolean isPerfect() {
        return score == UPPER_BOUND;
    }

    public boolean isEmpty() {
        return this.equals(EMPTY_SCORE);
    }

    public String printableScore() {
        if (score == LOWER_BOUND) {
            return GUTTER_SYMBOL;
        }
        return String.valueOf(score);
    }

    private static class EmptyBowlingScore extends BowlingScore {
        @Override
        public BowlingScore add(BowlingScore bowlingScore) {
            return bowlingScore;
        }

        @Override
        public boolean isPerfect() {
            return false;
        }

        @Override
        public boolean isEmpty() {
            return true;
        }

        @Override
        public String printableScore() {
            return "";
        }
    }
}


