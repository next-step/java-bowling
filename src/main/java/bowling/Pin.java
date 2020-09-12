package bowling;

public class Pin {
    public static final int DEFAULT_PIN_COUNT = 10;
    public static final int DEFAULT_TRY_COUNT = 0;

    private int pin;
    private int tryCount;

    private Pin() {
        pin = DEFAULT_PIN_COUNT;
        tryCount = DEFAULT_TRY_COUNT;
    }

    public static Pin from() {
        return new Pin();
    }

    public String hit(int count) {
        pin -= count;
        tryCount++;

        if (pin == 0 && tryCount == 1) {
            reset();
            return "X";
        }

        if (pin == 0) {
            return "/";
        }

        if (count == 0) {
            return "-";
        }

        return String.valueOf(count);
    }

    private void reset() {
        pin = DEFAULT_PIN_COUNT;
        tryCount = DEFAULT_TRY_COUNT;
    }
}
