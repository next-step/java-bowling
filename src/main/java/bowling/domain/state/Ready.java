package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;
import bowling.domain.State;

import java.util.List;

public class Ready extends Running {

    @Override
    public State roll(int count) {
        Pin pin = Pin.of(count);

        if (pin.isStrike()) {
            return new Strike(pin);
        }

        return new Hold(pin);
    }

    @Override
    public List<String> toValues() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Score getScore() {
        throw new UnsupportedOperationException();
    }
}
