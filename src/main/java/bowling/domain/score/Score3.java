package bowling.domain.score;

public class Score3 {

    private int value;
    private ScoreType2 scoreType;

    private Score3(int value, ScoreType2 scoreType) {
        this.value = value;
        this.scoreType = scoreType;
    }

    public static Score3 of(int value, ScoreType2 scoreType) {
        return new Score3(value, scoreType);
    }

    public int getValue() {
        return value;
    }

    public ScoreType2 getScoreType() {
        return scoreType;
    }
}
