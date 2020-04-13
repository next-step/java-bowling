package bowling.domain;

public enum Rule {
    MIN_PINS(0),
    MAX_PINS(10);

    private int value;

    Rule(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
