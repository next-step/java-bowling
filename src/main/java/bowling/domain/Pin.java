package bowling.domain;

public enum Pin {
    MAX(10),
    NONE(0);

    private int value;

    Pin(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
