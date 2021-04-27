package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;

public class FirstBowl implements State {

    private int countOfPin;
    public static final int PITCH = 1;

    public FirstBowl(int countOfPins) {
        this.countOfPin = countOfPins;
    }

    @Override
    public State bowl(int countOfPins) {
        if (countOfPins + this.countOfPin == Pin.MAX_PIN_COUNT) {
            return new Spare(this.countOfPin, countOfPins);
        }

        return new Miss(this.countOfPin, countOfPins);
    }

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public int getPitchCount() {
        return PITCH;
    }

    @Override
    public Score getScore() {
        throw new IllegalArgumentException(Ready.NOT_FINISH);
    }

    @Override
    public int getTotalCount() {
        return countOfPin;
    }

    @Override
    public String toString() {
        if (countOfPin == 0) {
            return "-";
        }
        return String.valueOf(countOfPin);
    }
}
