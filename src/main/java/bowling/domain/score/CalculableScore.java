package bowling.domain.score;

public class CalculableScore extends Score {
    protected CalculableScore(int score) {
        super(score);
    }

    public static Object from(int i) {
        return null;
    }

    @Override
    public boolean isCalculable() {
        return true;
    }
}
