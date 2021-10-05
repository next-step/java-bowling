package bowling.model.state;

import java.util.Objects;

import bowling.model.Pin;
import bowling.model.State;

public class Ready implements State {
    private final Pin countOfPin;
    
    public Ready() {
        this.countOfPin = new Pin(0);
    }

    @Override
    public State bowl(int countOfPin) {
        Pin currentPin = new Pin(countOfPin);

        if (currentPin.isStrike()) {
            return new Strike();
        }

        return new FirstBowl(countOfPin);
    }

    @Override
    public boolean isFinish(int frameNo) {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ready ready = (Ready) o;
        return Objects.equals(countOfPin, ready.countOfPin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countOfPin);
    }
}
