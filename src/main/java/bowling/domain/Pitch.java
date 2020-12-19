package bowling.domain;

public class Pitch {

    private static final int SCORE_CONDITION = 10;
    public static final int ZERO_CONDITION = 0;
    public static final String SCORE_LIMIT = "점수는 10점 이하입니다.";
    public static final String SCORE_OVER_ZERO = "점수는 0점 이상이어야 합니다.";

    private final int score;

    public Pitch(int score) {
        validateScore(score);
        this.score = score;
    }

    public static Pitch from(int score) {
        return new Pitch(score);
    }

    private void validateScore(int score) {
        validateScoreLimit(score);
        validateOverZero(score);
    }

    private void validateScoreLimit(int score) {
        if(score > SCORE_CONDITION) {
            throw new IllegalArgumentException(SCORE_LIMIT);
        }
    }

    private void validateOverZero(int score) {
        if(score < ZERO_CONDITION) {
            throw new IllegalArgumentException(SCORE_OVER_ZERO);
        }
    }

    public int getScore() {
        return score;
    }
}
