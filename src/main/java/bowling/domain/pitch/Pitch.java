package bowling.domain.pitch;

import bowling.domain.score.PitchScore;
import bowling.domain.score.ScoreType;
import bowling.domain.score.ScoreTypeFactory;

public class Pitch {

    private final PitchScore pitchScore;
    private final ScoreType scoreType;

    private Pitch(PitchScore pitchScore, ScoreType scoreType) {
        this.pitchScore = pitchScore;
        this.scoreType = scoreType;
    }

    public static Pitch initiate(PitchScore pitchScore) {
        ScoreType scoreType = ScoreTypeFactory.initiate(pitchScore);
        return new Pitch(pitchScore, scoreType);
    }

    public Pitch next(PitchScore pitchScore) {
        ScoreType scoreType = ScoreTypeFactory.next(this, pitchScore);
        return new Pitch(pitchScore, scoreType);
    }

    public boolean isStrike() {
        return scoreType == ScoreType.STRIKE;
    }

    public boolean isSpare() {
        return scoreType == ScoreType.SPARE;
    }

    public int calculateScoresSum(PitchScore nextPitchScore) {
        return this.getPitchScore() + nextPitchScore.getScore();
    }

    public int getPitchScore() {
        return pitchScore.getScore();
    }

    public String getScoreSignature() {
        return scoreType.getSignature(this);
    }
}
