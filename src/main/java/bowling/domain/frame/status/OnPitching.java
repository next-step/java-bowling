package bowling.domain.frame.status;

import bowling.bowlingexception.InvalidScoreCalculationException;

public abstract class OnPitching implements Status {

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public int calculateScore() {
        throw new InvalidScoreCalculationException();
    }
}
