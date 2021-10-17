package bowling.model.state;

import bowling.model.Score;

import java.util.Objects;

public class Pin {
    public static final int MAX_PINS = 10;
    private static final int MIN_PINS = 0;

    private final int falledPins;

    public Pin(int falledPins) {
        isValidRange(falledPins);
        this.falledPins = falledPins;
    }

    public static void isValidRange(int pinCount) {
        if(pinCount < MIN_PINS || pinCount > MAX_PINS) {
            throw new IllegalArgumentException("핀 갯수를 1개 이상 10개 이하로 입력해주세요.");
        }
    }

    public boolean isStrike() {
        return falledPins == MAX_PINS;
    }

    public boolean isGutter() {
        return falledPins == MIN_PINS;
    }

    public boolean isSpare(Pin secondPins) {
        return this.falledPins + secondPins.falledPins == MAX_PINS;
    }

    public Score sumScore(Score score) {
        return score.bowl(this.falledPins);
    }

    public int totalPins(Pin secondPins) {
        int totalPins = this.falledPins + secondPins.falledPins;
        isValidRange(totalPins);
        return totalPins;
    }

    @Override
    public String toString() {
        return (isGutter()) ? "-" : String.valueOf(falledPins);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pin pin1 = (Pin) o;
        return falledPins == pin1.falledPins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(falledPins);
    }

    String getDesc() {
        if (isStrike()) {
            return "X";
        }

        return falledPins + " | ";
    }

    String getDesc(Pin secondPins) {
        if (isSpare(secondPins)) {
            return falledPins + " | /";
        }

        return falledPins + " | " + secondPins.falledPins;
    }
}
