package bowling.domain.frame;

import bowling.domain.Score;
import bowling.domain.pin.NormalPins;
import bowling.domain.pin.Pins;

public class NormalFrame extends Frame {
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
        score.addScore(pin);
    }

    @Override
    public boolean isEnd() {
        return normalPins.isEnd();
    }

    @Override
    public int bonusAmount() {
        if (normalPins.isStrike()) {
            return STRIKE_BONUS;
        }
        if (normalPins.isSpare()) {
            return SPARE_BONUS;
        }
        return NO_BONUS;
    }

    @Override
    public void endScoring() {
        if (isEnd()) {
            score.endScoring();
        }
    }

    @Override
    public boolean endedScoring() {
        return score.endedScoring();
    }

    @Override
    public Pins pins() {
        return normalPins;
    }

}
