package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;
import bowling.domain.State;

import java.util.Arrays;
import java.util.List;

public class Final implements State {

    private Pin current;

    private Final(int count) {
        this.current = Pin.of(count);
    }

    public static State from(int count) {
        Pin pin = Pin.of(count);

        if (pin.isStrike()) {
            return new Strike(pin);
        }

        return new Final(count);
    }

    @Override
    public State roll(int count) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Pin> toPins() {
        return Arrays.asList(current);
    }

    @Override
    public boolean isFinish() {
        return true;
    }

    @Override
    public Score getScore() {
        return Score.ofMiss(current.getCount());
    }
}