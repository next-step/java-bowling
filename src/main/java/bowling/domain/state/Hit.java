package bowling.domain.state;

import bowling.domain.Pins;

import static bowling.domain.Pin.BOWLING_PIN_MAX_SIZE;

public class Hit implements FrameState {

    private final int pin;

    public Hit(int pin) {
        this.pin = pin;
    }

    @Override
    public FrameState bowl(int downPin) {
        Pins pins = Pins.valueOf(pin, downPin);
        if (pins.totalScore() == BOWLING_PIN_MAX_SIZE) {
            return new Spare(pin, downPin);
        }
        return new Miss(pin, downPin);
    }

    @Override
    public String printResult() {
        return printScore(pin) + BOWLING_STATE_SPLIT_DELIMITER;
    }
}
