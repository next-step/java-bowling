package bowling.domain.score;

public class Score {

    private int value;
    private ScoreType scoreType;

    private Score(int value, ScoreType scoreType) {
        this.value = value;
        this.scoreType = scoreType;
    }

    public static Score of(int value, ScoreType scoreType) {
        return new Score(value, scoreType);
    }

    public int getValue() {
        return value;
    }

    public ScoreType getScoreType() {
        return scoreType;
    }
}
