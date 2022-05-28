package bowling.domain;

import java.util.Objects;

public class Pins {
    private static final int MAX_PINS = 10;
    private static final int MIN_PINS = 0;

    private final int pins;

    public Pins(int fallPins) {
        validatePins(fallPins);
        this.pins = fallPins;
    }

    private void validatePins(int fallPins) {
        if (fallPins > MAX_PINS || fallPins < MIN_PINS) {
            throw new IllegalArgumentException("[ERROR] 볼링 핀은 " + MIN_PINS + "~" + MAX_PINS + "개여야 합니다.");
        }
    }

    public int sumScore(int score) {
        return pins + score;
    }

    public int pins() {
        return pins;
    }

    public boolean isStrike() {
        return pins == MAX_PINS;
    }

    public boolean isSpare(Pins secondPins) {
        validateAllPins(secondPins);
        return pins + secondPins.pins == MAX_PINS;
    }

    private void validateAllPins(Pins secondPins) {
        if (pins + secondPins.pins > MAX_PINS) {
            throw new IllegalArgumentException("[ERROR] 한 Frame에서는 최대 10개의 Pin을 쓰러뜨릴 수 있습니다.");
        }
    }

    public int totalScore(Pins secondPins) {
        return pins + secondPins.pins;
    }

    @Override
    public String toString() {
        return Integer.toString(pins);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pins pins1 = (Pins) o;
        return pins == pins1.pins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins);
    }
}
