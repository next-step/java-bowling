package bowling.domain.state;

import bowling.domain.Pin;

public class FirstBowl extends Running {
    private final Pin firstPins;

    public FirstBowl(int firstPins) {
        this.firstPins = new Pin(firstPins);
    }

    @Override
    public State pitch(int fallenPins) {
        if (firstPins.isSpare(new Pin(fallenPins))) {
            return new Spare(firstPins, new Pin(fallenPins));
        }

        return new Miss(firstPins, new Pin(fallenPins));
    }

    @Override
    public int getPitchCount() {
        return 1;
    }

    @Override
    public int getTotalCount() {
        return firstPins.getCount();
    }

    @Override
    public String toString() {
        return firstPins.toString();
    }
}
