package bowling.domain.score;

public class CalculableScore extends Score {
    protected CalculableScore(int score) {
        super(score);
    }

    @Override
    public boolean isCalculable() {
        return true;
    }
}
