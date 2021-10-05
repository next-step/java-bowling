package bowling.model;

import java.util.Objects;

public class Strike implements State {
    private final Point countOfPin;

    public Strike() {
        this.countOfPin = new Point(10);
    }

    @Override
    public State bowl(int countOfPin) {
        Point currentPin = new Point(countOfPin);

        if (currentPin.isStrike()) {
            return new SecondStrike();
        }

        return new SecondBowl(countOfPin);
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
