package bowling.domain.pitch;

import bowling.domain.score.Score;
import bowling.domain.score.ScoreType;
import bowling.domain.score.ScoreTypeFactory;

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

    public Pitch next(Score score) {
        ScoreType scoreType = ScoreTypeFactory.next(this, score);
        return new Pitch(score, scoreType);
    }

    public boolean isStrike() {
        return scoreType == ScoreType.STRIKE;
    }

    public boolean isSpare() {
        return scoreType == ScoreType.SPARE;
    }

    public int calculateScoresSum(Score nextScore) {
        return this.getScore() + nextScore.getScore();
    }

    public int getScore() {
        return score.getScore();
    }

    public String getScoreSignature() {
        return scoreType.getSignature(this);
    }
}
