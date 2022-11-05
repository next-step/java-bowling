package bowling.domain.frame.state;

public class Spare implements State {
    private static final String INVALID_PINS_EXCEPTION_MESSAGE = "스페어의 조건을 만족하지 않습니다.";

    private final Pins firstPins;
    private final Pins secondPins;

    public Spare(Pins firstPins, Pins secondPins) {
        validate(firstPins, secondPins);
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    @Override
    public State bowl(int pins) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isFinish() {
        return true;
    }

    @Override
    public boolean canBonusBowl() {
        return true;
    }

    private void validate(Pins firstPins, Pins secondPins) {
        if (!firstPins.isSpare(secondPins)) {
            throw new IllegalArgumentException(INVALID_PINS_EXCEPTION_MESSAGE);
        }
    }
}
