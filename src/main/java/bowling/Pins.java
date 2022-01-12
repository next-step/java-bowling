package bowling;

import java.util.Objects;

public class Pins {

    public static final int MAX_PINS_COUNT = 10;
    public static final int MIN_PINS_COUNT = 0;

    private final int fallenPins;

    public Pins(int fallenPins) {
        if (fallenPins > MAX_PINS_COUNT || fallenPins < MIN_PINS_COUNT) {
            throw new IllegalArgumentException(String.format("쓰러트릴 수 있는 핀의 개수는 %d개 이상 %d개 이하 입니다.", MIN_PINS_COUNT, MAX_PINS_COUNT));
        }
        this.fallenPins = fallenPins;
    }

    public boolean isStrike() {
        return fallenPins == MAX_PINS_COUNT;
    }


    public boolean isSpare(Pins afterPins) {
        return fallenPins + afterPins.fallenPins == MAX_PINS_COUNT;
    }

    public int getFallenPins() {
        return fallenPins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pins pins = (Pins) o;
        return getFallenPins() == pins.getFallenPins();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFallenPins());
    }

    @Override
    public String toString() {
        return "Pins{" +
                "fallenPins=" + fallenPins +
                '}';
    }
}
