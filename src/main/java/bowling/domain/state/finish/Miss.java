package bowling.domain.state.finish;

import bowling.domain.engine.State;

import static bowling.domain.Constants.BOWLING_STATE_SPLIT_DELIMITER;

public class Miss implements State {

    private final int firstPin;
    private final int downPin;

    public Miss(final int firstPin, final int downPin) {
        this.firstPin = firstPin;
        this.downPin = downPin;
    }

    @Override
    public State bowl(final int pin) {
        return new Miss(firstPin, pin);
    }

    @Override
    public String printResult() {
        return printScore(firstPin);
    }

    @Override
    public String printLastResult() {
        return printScore(firstPin) + BOWLING_STATE_SPLIT_DELIMITER + printScore(downPin);
    }
}
