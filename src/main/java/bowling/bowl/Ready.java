package bowling.bowl;

import bowling.score.Score;
import bowling.exception.CannotCalculateException;
import bowling.exception.RunningBowlException;
import bowling.pin.Pins;

public class Ready implements Bowl{
    @Override
    public Bowl pitch(Pins pins) {
        if(pins.isStrike()){
            return new Strike();
        }
        return new First(pins);
    }

    @Override
    public String getSymbol() {
        return "";
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
    public Score score() {
        throw new RunningBowlException();
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
