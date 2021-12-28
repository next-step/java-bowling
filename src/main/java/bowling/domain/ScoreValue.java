package bowling.domain;

public class ScoreValue {
    private static final int MIN = 0;
    private final int score;

    private ScoreValue(int score) {
        this.score = score;
    }

    public static ScoreValue init() {
        return new ScoreValue(MIN);
    }

    public static ScoreValue of(int score) {
        return new ScoreValue(score);
    }

    public int getScore() {
        return this.score;
    }

    public ScoreValue add(ScoreValue scoreValue) {
        return ScoreValue.of(score + scoreValue.score);
    }
}
