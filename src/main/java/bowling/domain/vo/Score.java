package bowling.domain.vo;

public class Score {
    private static final int MAX_SCORE = 300;
    private final int value;

    public Score(final int value) {
        if (value > MAX_SCORE) {
            throw new IllegalArgumentException(String.format("볼링점수는 %d를 넘길 수 없습니다", MAX_SCORE));
        }
        this.value = value;
    }

    public Score add(final Score score) {
        return new Score(this.value + score.value);
    }
}
