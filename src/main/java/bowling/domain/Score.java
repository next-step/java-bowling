package bowling.domain;

public class Score {
    private static final String MAX_OVER_SCORE = "프레임당 최대 점수는 30점 입니다.";
    private static final String MIN_UNDER_SCORE = "프레임당 최대 점수는 0점 입니다.";
    private static final int MAX_SCORE = 30;
    private static final int MIN_SCORE = 0;
    private final int score;

    private Score(int score) {
        validMaxScore(score);
        validMinScore(score);
        this.score = score;
    }

    private void validMinScore(int score) {
        if (MIN_SCORE > score) {
            throw new IllegalArgumentException(MIN_UNDER_SCORE);
        }
    }

    private void validMaxScore(int score) {
        if (MAX_SCORE < score) {
            throw new IllegalArgumentException(MAX_OVER_SCORE);
        }
    }

    public static Score of(int score) {
        return new Score(score);
    }

    public Score add(int score) {
        return new Score(this.score + score);
    }

    public int score() {
        return score;
    }
}
