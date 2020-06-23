package bowling.score;

import bowling.exception.BowlingBuildingException;
import bowling.pitch.Pitch;

public class ScoreTypeFactory {
    private static final int MAXIMUM_SCORE_TOTAL = 10;

    private ScoreTypeFactory() {
    }

    public static ScoreType initiate(Score score) {
        if (score.isMaximumScore()) {
            return ScoreType.STRIKE;
        }
        return score.isMinimumScore() ? ScoreType.GUTTER : ScoreType.NORMAL;
    }

    public static ScoreType next(Pitch lastPitch, Score score) {
        int scoresSum = lastPitch.calculateScoresSum(score);
        validateNextCondition(lastPitch, scoresSum);
        if (scoresSum == MAXIMUM_SCORE_TOTAL) {
            return ScoreType.SPARE;
        }
        return score.isMinimumScore() ? ScoreType.GUTTER : ScoreType.MISS;
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

/*
FinalPitches?
1) 첫 번째라면? new initiatePitch()
1) 이전 것이 Strike면? new initiatePitch() \X\NEW
2) 이전 것이 Spare면? new initiatePitch() \3//\NEW
3) 이전것이 miss면? \-|
 */
