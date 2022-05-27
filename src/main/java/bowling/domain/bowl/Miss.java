package bowling.domain.bowl;

import bowling.domain.score.Score;
import bowling.domain.pin.Pins;

public class Miss extends Ended{
    private final Pins firstPin;
    private final Pins secondPin;


    public Miss(Pins firstPin, Pins secondPin) {
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }

    @Override
    public String toString(){
        return "[Miss first: "+firstPin
                +" second: "+secondPin+"]";
    }

    @Override
    public String getSymbol() {
        return firstPin.getSymbol()+"|"+secondPin.getSymbol();
    }

    @Override
    public Score score() {
        return Score.miss(firstPin.getCount(), secondPin.getCount());
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
