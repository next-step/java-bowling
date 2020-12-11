package bowling.domain;

public class KnockDownPins {
    private final int value;

    private KnockDownPins(int value) {
        this.value = value;
    }

    public static KnockDownPins valueOf(int value) {
        return new KnockDownPins(value);
    }
}
