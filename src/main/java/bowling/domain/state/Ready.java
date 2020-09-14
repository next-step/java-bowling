package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Result;
import bowling.domain.State;

import java.util.Arrays;
import java.util.List;

public class Ready implements State {

    private Pin current;

    public Ready() {
    }

    @Override
    public State roll(int count) {
        Pin pin = Pin.of(count);

        if (pin.isStrike()) {
            return new Strike(pin);
        }

        current = pin;

        if (current.isSpare(count)) {
            return new Spare(current, count);
        }

        return new Open(current, count);
    }

    @Override
    public List<String> value() {
        if (current.isGutter()) {
            return Arrays.asList(Result.GUTTER.toString());
        }
        return Arrays.asList(current.toString());
    }
}
