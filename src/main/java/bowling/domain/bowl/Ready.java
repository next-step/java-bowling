package bowling.domain.bowl;

import bowling.domain.frame.PitchResult;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;
import bowling.domain.exception.CannotCalculateException;
import bowling.domain.exception.RunningBowlException;

public class Ready implements Bowl{
    @Override
    public Bowl pitch(Pins pins) {
        if(pins.isStrike()){
            return new Strike();
        }
        return new First(pins);
    }

    @Override
    public String toString(){
        return "[Ready]";
    }

    @Override
    public Score calculateScore(Score before) {
        throw new CannotCalculateException();
    }

    @Override
    public PitchResult getPitchResult() {
        return null;
    }

    @Override
    public Score score() {
        throw new RunningBowlException();
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
