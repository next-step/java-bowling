package bowling.domain.score;

public class ComputableScore extends CommonScore {
    protected ComputableScore(int score) {
        super(score);
    }

    public static ComputableScore of(int score) {
        return new ComputableScore(score);
    }

    public static ComputableScore of(Score score) {
        return new ComputableScore(score.getValue());
    }

    @Override
    public boolean isCompute() {
        return true;
    }

    @Override
    public Score add(Score anotherScore) {
        throw new IllegalStateException("Calculable score can't add more score");
    }
}
