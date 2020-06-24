package bowling.domain.state.finish;

import bowling.domain.pin.PinCount;
import bowling.domain.pin.Pins;
import bowling.domain.state.State;
import bowling.exception.message.ErrorMessage;

import java.util.Collections;
import java.util.List;

public abstract class Finished implements State {

    @Override
    public State bowl(PinCount hitCount) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isFinish() {
        return true;
    }

    @Override
    public boolean isMiss() {
        return false;
    }

    @Override
    public boolean isCleanState() {
        return this.isFinish() && !this.isMiss();
    }

    public Pins getSecondPins() {
        throw new IllegalArgumentException(ErrorMessage.NOT_ALLOW_SECOND_BOWL);
    }

    @Override
    public List<State> getState() {
        return Collections.singletonList(this);
    }
}
