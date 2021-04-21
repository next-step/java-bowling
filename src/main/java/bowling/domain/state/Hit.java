package bowling.domain.state;

import bowling.domain.Pins;

public class Hit extends BaseState {

    public Hit(int pin) {
        super(pin);
    }

    @Override
    public FrameState bowl(int downPin) {
        Pins pins = Pins.valueOf(firstPin, downPin);
        if (pins.isSpare()) {
            return new Spare(firstPin, downPin);
        }
        return new Miss(firstPin, downPin);
    }

    @Override
    public String printResult() {
        return printScore(firstPin) + BOWLING_STATE_SPLIT_DELIMITER;
    }

    @Override
    public int totalScore() {
        return firstPin;
    }
}
