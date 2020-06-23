package bowling.score;

import bowling.exception.BowlingBuildingException;
import bowling.pitch.Pitch;

public class ScoreTypeFactory {

    private ScoreTypeFactory() {
    }

    public static ScoreType initiate(Score score) {
        if (score.isMaximumScore()) {
            return ScoreType.STRIKE;
        }
        return score.isMinimumScore() ? ScoreType.GUTTER : ScoreType.NORMAL;
    }

    public static ScoreType next(Pitch pitch, Score score) {
        if (pitch.isStrike()) {
            throw new BowlingBuildingException(BowlingBuildingException.INVALID_NORMAL_PITCHES);
        }
        if (pitch.getScore() + score.getScore() > 10) {
            throw new BowlingBuildingException((BowlingBuildingException.OVER_SCORE));
        }
        if (pitch.getScore() + score.getScore() == 10) {
            return ScoreType.SPARE;
        }
        return score.isMinimumScore() ? ScoreType.GUTTER : ScoreType.MISS;
    }
}
