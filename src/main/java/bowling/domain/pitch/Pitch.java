package bowling.domain.pitch;

import bowling.domain.score.PitchScore;
import bowling.domain.score.PitchScoreType;

public class Pitch {

    private final PitchScore pitchScore;
    private final PitchScoreType pitchScoreType;

    private Pitch(PitchScore pitchScore, PitchScoreType pitchScoreType) {
        this.pitchScore = pitchScore;
        this.pitchScoreType = pitchScoreType;
    }

    public static Pitch initiate(PitchScore pitchScore) {
        PitchScoreType pitchScoreType = PitchScoreType.initiate(pitchScore);
        return new Pitch(pitchScore, pitchScoreType);
    }

    public Pitch next(PitchScore pitchScore) {
        PitchScoreType pitchScoreType = PitchScoreType.next(this, pitchScore);
        return new Pitch(pitchScore, pitchScoreType);
    }

    public boolean isStrike() {
        return pitchScoreType == PitchScoreType.STRIKE;
    }

    public boolean isSpare() {
        return pitchScoreType == PitchScoreType.SPARE;
    }

    public int calculateScoresSum(PitchScore nextPitchScore) {
        return this.getPitchScore() + nextPitchScore.getPitchScore();
    }

    public int getPitchScore() {
        return pitchScore.getPitchScore();
    }

    public String getScoreSignature() {
        return pitchScoreType.getSignature(this);
    }
}
