package bowling.domain;

public class Frame {

    private final int round;
    private final Pins pins;

    public Frame(int round, Pins pins) {
        this.round = round;
        this.pins = pins;
    }

    public static Frame of() {
        return null;
    }
}
