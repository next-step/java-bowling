package bowling.domain.state;

import org.apache.logging.log4j.util.Strings;

public abstract class BaseState implements FrameState {

    protected final int firstPin;

    BaseState(int firstPin) {
        this.firstPin = firstPin;
    }

    @Override
    public FrameState bowl(int pin) {
        return new Ready();
    }

    @Override
    public String printResult() {
        return Strings.EMPTY;
    }

    protected String printScore(int pin) {
        if (pin == 0) {
            return BOWLING_PRINT_GUTTER;
        }
        return String.valueOf(pin);
    }

    @Override
    public int totalScore() {
        return 0;
    }
}
