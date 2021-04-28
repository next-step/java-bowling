package bowling.domain;

public class Score {

    private static final int MAX_SCORE = 300;

    private final int score;

    public Score(int score) {
        if (score > MAX_SCORE) {
            throw new IllegalArgumentException("가능한 최대 점수는 300점입니다.");
        }
        this.score = score;
    }

    public int score() {
        return score;
    }
}
