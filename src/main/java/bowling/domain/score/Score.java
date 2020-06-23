package bowling.domain.score;

public class Score {

    private final int score;

    public Score(int score) {
        this.score = score;
    }

    public static Score of(int score) {
        validate(score);
        return new Score(score);
    }

    private static void validate(int score) {
        if (score < 0 || score > 10) {
            throw new IllegalArgumentException("점수는 0~10이어야 합니다");
        }
    }
}
