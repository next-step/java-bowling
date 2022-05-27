package bowling.domain.bowl;

import bowling.domain.frame.PitchResult;
import bowling.domain.score.Score;
import bowling.domain.pin.Pins;

public class First implements Bowl{

    private final Pins prePins;

    public First(Pins prePins) {
        this.prePins = prePins;
    }


    @Override
    public Bowl pitch(Pins pins) {
        if(Pins.isSpare(prePins, pins)){
            return new Spare(prePins, pins);
        }
        if(Pins.isGutter(prePins, pins)){
            return new Gutter();
        }
        return new Miss(prePins, pins);
    }

    @Override
    public String toString(){
        return "[First first: "+prePins+"]";
    }

    @Override
    public Score calculateScore(Score before) {
        return before.addValue(prePins.getCount());
    }

    @Override
    public PitchResult getPitchResult() {
        return PitchResult.first(prePins.getCount());
    }

    @Override
    public Score score() {
        return new Score(prePins.getCount(), 1);
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
