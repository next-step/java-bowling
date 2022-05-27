package bowling.bowl;

import bowling.score.Score;
import bowling.pin.Pins;

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
    public String getSymbol() {
        return firstPin.getSymbol()+"|/";
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

}
