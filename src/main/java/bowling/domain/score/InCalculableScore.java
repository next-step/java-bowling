package bowling.domain.score;

public class InCalculableScore extends Score {
    protected InCalculableScore(int score) {
        super(score);
    }

    public static InCalculableScore init() {
        return new InCalculableScore(0);
    }
}
