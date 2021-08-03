package bowling.domain.score;

public class CalculableScore extends Score {
    protected CalculableScore(int score) {
        super(score);
    }

    public static CalculableScore from(int score) {
        return new CalculableScore(score);
    }

    public static CalculableScore from(Score score) {
        return new CalculableScore(score.score);
    }

    @Override
    public boolean isCalculable() {
        return true;
    }

    @Override
    public Score add(Score anotherScore) {
        throw new IllegalStateException("Calculable score can't add more score");
    }
}
