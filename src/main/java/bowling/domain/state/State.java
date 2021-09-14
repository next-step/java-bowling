package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;

import java.util.Objects;

public abstract class State {
    protected Pin firstPin;
    protected Pin secondPin;
    protected Score score;

    public abstract State bowl(int pin);

    public abstract Score getScore();

    public abstract boolean finish();

    public Pin getFirstPin() {
        return firstPin;
    }

    public Pin getSecondPin() {
        return secondPin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return Objects.equals(firstPin, state.firstPin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPin);
    }
}
