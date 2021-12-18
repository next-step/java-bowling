package bowling;

public class Score {

    private static final int MIN_SCORE = 0;
    private static final int MAX_SCORE = 300;

    private final int score;

    public Score(int score) {
        valid(score);

        this.score = score;
    }

    private void valid(int score) {
        if (score < MIN_SCORE) {
            throw new IllegalArgumentException("점수는 음수가 올 수 없어요");
        }

        if (score > MAX_SCORE) {
            throw new IllegalArgumentException("볼링점수의 최대값을 넘겼어요");
        }

    }
}
