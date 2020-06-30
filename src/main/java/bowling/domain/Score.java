package bowling.domain;

import java.util.List;

public class Score {

    private int score;
    public static final int MAX_SCORE = 10;
    public static final int MIN_SCORE = 0;

    public Score(int score) {
        validateInputMinScore(score);
        validateInputMaxScore(score);
        this.score = score;
    }

    public Score(int score, List<Score> scores) {
        validateInputMinScore(score);
        validateInputMaxScore(score);
        validateInputSecondScore(score, scores);
        this.score = score;
    }

    private void validateInputSecondScore(int score, List<Score> scores) {
        if (scores.size() > 0) {
            validateInputMaxScore(scores.get(scores.size() - 1).getScore() + score);
        }

    }

    private void validateInputMinScore(int score) {
        if (MIN_SCORE > score) {
            throw new IllegalArgumentException("0 ~ 10 점수 입력");
        }
    }

    private void validateInputMaxScore(int score) {
        if (MAX_SCORE < score) {
            throw new IllegalArgumentException("0 ~ 10 점수 입력");
        }
    }

    public boolean validateMaxScore() {
        return score == MAX_SCORE;
    }

    public boolean validateMinScore() {
        return score == MIN_SCORE;
    }

    public int getScore() {
        return this.score;
    }
}
