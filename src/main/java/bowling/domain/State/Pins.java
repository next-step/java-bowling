package bowling.domain.State;

public class Pins {
    private static final int MIN_PINS = 0;
    private static final int MAX_PINS = 10;
    private static final String STRIKE = "X";
    private static final String SPARE = "/";
    private static final String DELIMITER = "|";
    private static final String GUTTER = "-";

    private int felledPins;

    public Pins(int felledPins) {
        this.felledPins = felledPins;
    }

    public static Pins bowl(int felledPins) {
        validatePins(felledPins);
        return new Pins(felledPins);
    }

    boolean isStrike() {
        return this.felledPins == MAX_PINS;
    }

    boolean isSpare(Pins secondPins) {
        return totalPins(secondPins) == MAX_PINS;
    }

    public int totalPins(Pins secondPins) {
        int totalPins = this.felledPins + secondPins.felledPins;
        validatePins(totalPins);
        return totalPins;
    }

    public String getDisplayPins(int felledPins) {
        if (felledPins == MIN_PINS) {
            return GUTTER;
        }
        return String.valueOf(felledPins);
    }

    private static void validatePins(int pins) {
        if (pins > MAX_PINS) {
            throw new IllegalArgumentException("쓰러트릴 수 있는 핀의 숫자는 10을 넘을 수 없습니다.");
        }
        if (pins < MIN_PINS) {
            throw new IllegalArgumentException("쓰러트릴 수 있는 핀의 숫자는 0보다 작을 수 없습니다.");
        }
    }

    public String getDesc() {
        if (isStrike()) {
            return STRIKE;
        }
        return getDisplayPins(felledPins);
    }

    public String getDesc(Pins secondPins) {
        if (isSpare(secondPins)) {
            return felledPins + DELIMITER + SPARE;
        }
        return getDisplayPins(felledPins) + DELIMITER + getDisplayPins(secondPins.felledPins);
    }

    public int getFelledPins() {
        return felledPins;
    }
}
