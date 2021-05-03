package bowling.domain;

public class ScoreState {
    public static ScoreState ofNone() {
        return new ScoreState();
    }

    public static ScoreState ofSpare() {
        return new ScoreState();
    }

    public static ScoreState ofStrike() {
        return new ScoreState();
    }

    public boolean canCalculate() {
        return false;
    }
}
