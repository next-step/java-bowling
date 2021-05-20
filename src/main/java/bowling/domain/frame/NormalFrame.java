package bowling.domain.frame;

import bowling.domain.Score;
import bowling.domain.pin.NormalPins;
import bowling.domain.pin.Pins;

public class NormalFrame implements Frame {
    private Score score;
    private NormalPins normalPins;

    public NormalFrame() {
        score = new Score();
        normalPins = new NormalPins();
    }

    @Override
    public void addPoint(int bonusPoint) {
        score.addScore(bonusPoint);
    }

    @Override
    public void bowl(int pin) {
        normalPins.bowl(pin);
        if(normalPins.isStrike() || normalPins.isSpare()){
            score.addBonus();
        }
    }

    @Override
    public boolean isEnd() {
        return normalPins.isEnd();
    }

    @Override
    public boolean endedScoring() {
        return normalPins.isEnd() && !score.hasBonus();
    }

    @Override
    public Pins pins() {
        return normalPins;
    }

    @Override
    public Score score() {
        return score;
    }

}
