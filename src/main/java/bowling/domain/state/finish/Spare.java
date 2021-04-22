package bowling.domain.state.finish;

import bowling.domain.engine.State;
import bowling.domain.state.run.Ready;

import static bowling.domain.Constants.BOWLING_STATE_SPARE;
import static bowling.domain.Constants.BOWLING_STATE_SPLIT_DELIMITER;
import static bowling.domain.pin.Pin.BOWLING_PIN_MAX_SIZE;

public class Spare implements State {

    private final int firstPin;
    private final int secondPin;

    public Spare(final int firstPin, final int secondPin) {
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }

    @Override
    public State bowl(final int pin) {
        if (firstPin + pin == BOWLING_PIN_MAX_SIZE) {
            return new Spare(firstPin, pin);
        }
        return new Ready().bowl(pin);
    }

    @Override
    public String printResult() {
        return printScore(firstPin);
    }

    @Override
    public String printLastResult() {
        return printScore(firstPin) + BOWLING_STATE_SPLIT_DELIMITER + BOWLING_STATE_SPARE;
    }

}
