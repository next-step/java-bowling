package bowling.domain.state.run;

import bowling.domain.engine.State;
import bowling.domain.state.finish.Miss;
import bowling.domain.state.finish.Spare;

import static bowling.domain.Constants.BOWLING_STATE_SPLIT_DELIMITER;
import static bowling.domain.pin.Pin.BOWLING_PIN_MAX_SIZE;

public class Hit implements State {

    private final int firstPin;

    public Hit(final int downPin) {
        this.firstPin = downPin;
    }

    @Override
    public State bowl(final int downPin) {
        if (firstPin + downPin == BOWLING_PIN_MAX_SIZE) {
            return new Spare(firstPin, downPin);
        }
        return new Miss(firstPin, downPin);
    }

    @Override
    public String printResult() {
        return printScore(firstPin);
    }

    @Override
    public String printLastResult() {
        return printScore(firstPin) + BOWLING_STATE_SPLIT_DELIMITER;
    }
}
