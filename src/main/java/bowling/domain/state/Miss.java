package bowling.domain.state;

import bowling.domain.Pin;

public class Miss extends Finished {
    private final Pin firstPin;
    private final Pin secondPin;

    public Miss(int firstPin, int secondPin) {
        this(new Pin(firstPin), new Pin(secondPin));
    }

    public Miss(Pin firstPin, Pin secondPin) {
        this.validate(firstPin, secondPin);

        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }

    private void validate(Pin firstPin, Pin secondPin) {
        if (firstPin.getCount() + secondPin.getCount() >= 10) {
            throw new IllegalArgumentException("잘못된 투구입니다.");
        }
    }

    @Override
    public State pitch(int fallenPins) {
        throw new UnsupportedOperationException("더이상 투구할 수 없습니다.");
    }

    @Override
    public String toString() {
        return firstPin.toString() + "|" + secondPin.toString();
    }
}
