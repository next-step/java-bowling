package bowling.domain.score;

public class CalculableScore extends Score {
    protected CalculableScore(int score) {
        super(score);
    }

    public static CalculableScore from(int score) {
        return new CalculableScore(score);
    }

    @Override
    public boolean isCalculable() {
        return true;
    }
}
