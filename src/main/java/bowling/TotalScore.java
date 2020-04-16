package bowling;

public class TotalScore {

    private final int score;

    private TotalScore(final int score) {
        this.score = score;
    }

    public static TotalScore of(final int score) {
        return new TotalScore(score);
    }
}
