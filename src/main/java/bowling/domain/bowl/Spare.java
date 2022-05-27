package bowling.domain.bowl;

import bowling.domain.pitch.PitchResult;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;

public class Spare extends Ended{

    private final Pins firstPin;
    private final Pins secondPin;


    public Spare(Pins firstPin, Pins secondPin) {
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }


    @Override
    public String toString(){
        return "[Spare first: "+firstPin
                +" second: "+secondPin+"]";
    }

    @Override
    public Score score() {
        return Score.spare(firstPin.getCount(), secondPin.getCount());
    }

    @Override
    public Score calculateScore(Score before) {
        Score after = before.addValue(firstPin.getCount());
        if(after.isFinished()){
            return after;
        }
        return after.addValue(secondPin.getCount());
    }

    @Override
    public PitchResult getPitchResult() {
        return PitchResult.spare(firstPin.getCount(), secondPin.getCount());
    }

}
