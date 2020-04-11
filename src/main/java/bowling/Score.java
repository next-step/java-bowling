package bowling;

public class Score {
    private final int score;

    private Score(final int score) {
        this.score = score;
    }

    public static Score of(final int score) {
        validate(score);
        return new Score(score);
    }

    private static void validate(final int score) {
        if(score < 0) {
            throw new IllegalArgumentException("Score must be greater than zero");
        }
    }
}
