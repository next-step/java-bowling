package bowling.domain.frame;

import bowling.domain.Score;
import bowling.domain.pin.FinalPins;
import bowling.domain.pin.Pins;

public class FinalFrame implements Frame {

    private static final int ONE = 1;
    private static final int TWO = 2;

    private Score score;
    private Pins finalPins;

    public FinalFrame() {
        score = new Score();
        finalPins = new FinalPins();
    }

    @Override
    public void addPoint(int bonusPoint) {
        score.addScore(bonusPoint);
    }

    @Override
    public void bowl(int pin) {
        finalPins.bowl(pin);
        if(finalPins.isStrike() && finalPins.size()==ONE){
            score.addBonus();
        }
        if(finalPins.isSpare() && finalPins.size()==TWO){
            score.addBonus();
        }
    }

    @Override
    public boolean isEnd() {
        return finalPins.isEnd();
    }

    @Override
    public boolean endedScoring() {
        return finalPins.isEnd() && !score.hasBonus();
    }

    @Override
    public Pins pins() {
        return finalPins;
    }

    @Override
    public Score score() {
        return score;
    }
}
