package bowling.domain;

public class Score {
    private static final int MAX = 10;

    private final int score;

    public Score(int score) {
        if (score < 0 || score > MAX) {
            throw new IllegalArgumentException(String.format("볼링 점수는 1에서 %s 사이이어야 합니다.", MAX));
        }

        this.score = score;
    }

    public int score() {
        return score;
    }

    public boolean isMax() {
        return score == MAX;
    }
}
