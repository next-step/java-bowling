package bowling.refactor;

public class Score {

    private final int score;

    private Score(final int score) {
        validateScoreRange(score);
        this.score = score;
    }

    private static void validateScoreRange(final int score) {
        if (score < 0) {
            throw new IllegalArgumentException("Score must be greater than zero");
        }
    }

    public static Score of(final int score) {
        return new Score(score);
    }

    public Score add(final int score) {
        return new Score(this.score + score);
    }
}
