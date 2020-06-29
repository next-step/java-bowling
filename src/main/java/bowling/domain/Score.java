package bowling.domain;

public class Score {

    private int score;
    public static final int MAX_SCORE = 10;
    public static final int MIN_SCORE = 0;

    public Score(int score) {
        validateInputScore(score);
        this.score = score;
    }

    private void validateInputScore(int score) {
        if (MIN_SCORE > score || MAX_SCORE < score) {
            throw new IllegalArgumentException("0 ~ 10 점수 입력");
        }
    }

    public boolean validateMaxScore() {
        return score == MAX_SCORE;
    }

    public int getScore() {
        return this.score;
    }
}
