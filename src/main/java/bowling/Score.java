package bowling;

public class Score {
    private final int score;

    private Score(final int score) {
        validate(score);
        this.score = score;
    }

    private static void validate(final int score) {
        if (score < 0 || score > 10) {
            throw new IllegalArgumentException("Score must be greater than zero and lower than 10.");
        }
    }

    private void validateSecondPitching(final int score) {
        int sumOfScores = this.score + score;

        if (sumOfScores < 0 || sumOfScores > 10) {
            throw new IllegalArgumentException("Sum of first, second pitching score must be greater than zero and lower than 10.");
        }
    }

    public static Score of(final int score) {
        return new Score(score);
    }

    public Score secondPitching(final int score) {
        validateSecondPitching(score);
        return new Score(score);
    }

}
