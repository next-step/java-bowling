package bowling.domain.state;

import java.util.Objects;

public class BowlingPin {
    private static final int MAX_PIN_COUNT = 10;
    private static final int MIN_PIN_COUNT = 0;

    private final int bowlingPin;

    private BowlingPin(int bowlingPin) {
        validate(bowlingPin);
        this.bowlingPin = bowlingPin;
    }

    public static BowlingPin of(int bowlingPin) {
        return new BowlingPin(bowlingPin);
    }

    private void validate(int bowlingPin) {
        if (bowlingPin < MIN_PIN_COUNT || bowlingPin > MAX_PIN_COUNT) {
            throw new IllegalArgumentException("유효하지 않은 볼링핀 입니다.");
        }
    }

    public boolean isMax() {
        return this.bowlingPin == MAX_PIN_COUNT;
    }

    public boolean isMin() {
        return this.bowlingPin == MIN_PIN_COUNT;
    }

    public BowlingPin sum(BowlingPin bowlingPin) {
        return of(this.bowlingPin + bowlingPin.bowlingPin);
    }

    public int score() {
        return bowlingPin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        BowlingPin that = (BowlingPin)o;
        return bowlingPin == that.bowlingPin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bowlingPin);
    }

    @Override
    public String toString() {
        return Integer.toString(bowlingPin);
    }
}
