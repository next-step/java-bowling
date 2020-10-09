package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;

public class Spare extends Finished {
    private final Pin firstPins;
    private final Pin secondPins;

    public Spare(int firstPins, int secondPins) {
        this(new Pin(firstPins), new Pin(secondPins));
    }

    public Spare(Pin firstPins, Pin secondPins) {
        this.validate(firstPins, secondPins);

        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    private void validate(Pin firstPins, Pin secondPins) {
        if (!firstPins.validate(secondPins) || !firstPins.isSpare(secondPins)) {
            throw new IllegalArgumentException("유효하지 않은 투구입니다.");
        }
    }

    @Override
    public int getPitchCount() {
        return 2;
    }

    @Override
    public Score getScore() {
        return Score.ofSpare();
    }

    @Override
    public int getTotalCount() {
        return 10;
    }

    @Override
    public String toString() {
        return firstPins.toString() + "|/";
    }
}
