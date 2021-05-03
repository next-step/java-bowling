package bowling.domain.score;

public class ScoreState {

    private static final int NONE_STATE = 0;
    private static final int SPARE_STATE = 1;
    private static final int STRIKE_STATE = 2;

    private final int leftOpportunity;

    private ScoreState(int leftOpportunity) {
        this.leftOpportunity = leftOpportunity;
    }

    public static ScoreState ofNone() {
        return new ScoreState(NONE_STATE);
    }

    public static ScoreState ofSpare() {
        return new ScoreState(SPARE_STATE);
    }

    public static ScoreState ofStrike() {
        return new ScoreState(STRIKE_STATE);
    }

    public ScoreState changeState() {
        return new ScoreState(leftOpportunity - 1);
    }

    public boolean canCalculate() {
        return leftOpportunity == NONE_STATE;
    }
}
