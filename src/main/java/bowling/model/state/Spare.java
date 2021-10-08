package bowling.model.state;

import java.util.Objects;

import static bowling.controller.Main.stateResult;

import bowling.model.Pin;
import bowling.model.State;

public class Spare implements State {
    private final Pin countOfPin;

    public Spare() {
        this.countOfPin = new Pin(10);
    }

    @Override
    public State bowl(int countOfPin) {
        Pin currentPin = new Pin(countOfPin);

        if (currentPin.isStrike()) {
            return new ThirdStrike();
        }

        return new Miss(this.countOfPin, new Pin(countOfPin));
    }

    @Override
    public boolean isFinish(int frameNo) {
        return frameNo != FINAL_FRAME_NO;
    }

    @Override
    public String toString() {
        return stateResult.removeLast() + "|/";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spare spare = (Spare) o;
        return Objects.equals(countOfPin, spare.countOfPin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countOfPin);
    }
}
