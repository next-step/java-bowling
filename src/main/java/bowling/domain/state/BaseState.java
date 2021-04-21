package bowling.domain.state;

import org.apache.logging.log4j.util.Strings;

public abstract class BaseState implements FrameState {

    @Override
    public FrameState bowl(int pin) {
        return new Ready();
    }

    @Override
    public String printResult() {
        return Strings.EMPTY;
    }

    @Override
    public String printScore(int pin) {
        return FrameState.super.printScore(pin);
    }
}
