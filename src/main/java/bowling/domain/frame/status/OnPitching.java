package bowling.domain.frame.status;

import bowling.bowlingexception.InvalidScoreCalculationException;
import bowling.domain.score.Score;

public abstract class OnPitching implements Status {

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public Score calculateBaseScoreOfFrame() {
        throw new InvalidScoreCalculationException();
    }
}
