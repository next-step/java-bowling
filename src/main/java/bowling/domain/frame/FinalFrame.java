package bowling.domain.frame;

import bowling.domain.pin.FinalPins;

public class FinalFrame extends Frame {
    private int score;
    private FinalPins finalPins;

    public FinalFrame() {
        score = INIT_SCORE;
        finalPins = new FinalPins();
    }

    @Override
    public void addPoint(int bonusPoint) {
        score+=bonusPoint;
    }

    @Override
    public void bowl(int pin) {
        finalPins.bowl(pin);
        score+=pin;
    }

    @Override
    public boolean isEnd() {
        return finalPins.isEnd();
    }

    @Override
    public int bonusAmount() {
        if (finalPins.isStrike()) {
            return STRIKE_BONUS;
        }
        if (finalPins.isSpare()) {
            return SPARE_BONUS;
        }
        return NO_BONUS;
    }
}
