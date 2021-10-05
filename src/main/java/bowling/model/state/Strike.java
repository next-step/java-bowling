package bowling.model.state;

import java.util.Objects;

import bowling.model.Pin;
import bowling.model.State;

public class Strike implements State {
    private final Pin countOfPin;

    public Strike() {
        this.countOfPin = new Pin(10);
    }

    @Override
    public State bowl(int countOfPin) {
        Pin currentPin = new Pin(countOfPin);

        if (currentPin.isStrike()) {
            return new SecondStrike();
        }

        return new SecondBowl(countOfPin);
    }

    @Override
    public String toString() {
        return "X";
    }

    @Override
    public boolean isFinish(int frameNo) {
        return frameNo != FINAL_FRAME_NO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Strike strike = (Strike) o;
        return Objects.equals(countOfPin, strike.countOfPin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countOfPin);
    }

}
