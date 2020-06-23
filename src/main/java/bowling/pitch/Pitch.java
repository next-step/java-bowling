package bowling.pitch;

import bowling.score.Score;
import bowling.score.ScoreType;
import bowling.score.ScoreTypeFactory;

public class Pitch {

    private final Score score;
    private final ScoreType scoreType;

    private Pitch(Score score, ScoreType scoreType) {
        this.score = score;
        this.scoreType = scoreType;
    }

    public static Pitch initiate(Score score) {
        ScoreType scoreType = ScoreTypeFactory.initiate(score);
        return new Pitch(score, scoreType);
    }

    public boolean isStrike() {
        return scoreType == ScoreType.STRIKE;
    }

    public int getScore() {
        return score.getScore();
    }

    public ScoreType getScoreType() {
        return scoreType;
    }
}
