package bowling.common;

public enum Pin {

    NONE(-1), MAX(10), NO_POINT(0);

    private final int value;

    Pin(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

}
