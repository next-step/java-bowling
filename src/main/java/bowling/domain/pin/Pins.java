package bowling.domain.pin;

public class Pins {

    public static final int MIN_COUNT_FALLEN_PINS = 0;
    public static final int MAX_COUNT_FALLEN_PINS = 10;

    private final int countFallenPins;

    private Pins(int countFallenPins) {
        validate(countFallenPins);

        this.countFallenPins = countFallenPins;
    }

    private void validate(int countFallenPins) {
        if (countFallenPins < MIN_COUNT_FALLEN_PINS || countFallenPins > MAX_COUNT_FALLEN_PINS) {
            throw new IllegalArgumentException(
                    "핀의 개수는 " + MIN_COUNT_FALLEN_PINS + " ~ " + MAX_COUNT_FALLEN_PINS + " 사이입니다."
            );
        }
    }

    public static Pins of(int numOfDownedPins) {
        return new Pins(numOfDownedPins);
    }

    public Pins add(Pins pins) {
        return new Pins(countFallenPins + pins.countFallenPins);
    }

    public int getCountFallenPins() {
        return countFallenPins;
    }

    public boolean isAllFallen() {
        return countFallenPins == MAX_COUNT_FALLEN_PINS;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pins pins = (Pins) o;

        return countFallenPins == pins.countFallenPins;
    }

    @Override
    public int hashCode() {
        return countFallenPins;
    }
}
