package bowling.domain.state;

import bowling.domain.Pin;

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
    public State pitch(int fallenPins) {
        throw new UnsupportedOperationException("더이상 투구할 수 없습니다.");
    }

    @Override
    public String toString() {
        return firstPins.toString() + "|/";
    }
}
