package bowling.domain.score;

import bowling.domain.exception.BowlingBuildingException;
import bowling.domain.pitch.Pitch;

public class ScoreTypeFactory {
    private static final int MAXIMUM_SCORE_TOTAL = 10;

    private ScoreTypeFactory() {
    }

    public static ScoreType initiate(PitchScore pitchScore) {
        if (pitchScore.isMaximumScore()) {
            return ScoreType.STRIKE;
        }
        return pitchScore.isMinimumScore() ? ScoreType.GUTTER : ScoreType.NORMAL;
    }

    public static ScoreType next(Pitch lastPitch, PitchScore pitchScore) {
        int scoresSum = lastPitch.calculateScoresSum(pitchScore);
        validateNextCondition(lastPitch, scoresSum);
        if (scoresSum == MAXIMUM_SCORE_TOTAL) {
            return ScoreType.SPARE;
        }
        return pitchScore.isMinimumScore() ? ScoreType.GUTTER : ScoreType.MISS;
    }

    private static void validateNextCondition(Pitch lastPitch, int scoresSum) {
        if (lastPitch.isStrike() || lastPitch.isSpare()) {
            throw new BowlingBuildingException(BowlingBuildingException.INVALID_NORMAL_PITCHES_SCORE);
        }
        if (scoresSum > MAXIMUM_SCORE_TOTAL) {
            throw new BowlingBuildingException((BowlingBuildingException.OVER_SCORE));
        }
    }
}
