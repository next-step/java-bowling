package bowling.domain;

public class NormalFrame implements Frame {

    private final int round;
    private final Pins pins;

    public NormalFrame(int round, Pins pins) {
        this.round = round;
        this.pins = pins;
    }

    public static Frame create() {
        return new NormalFrame(0, Pins.of());
    }

    @Override
    public Frame roll(int downPins) {
        pins.roll(downPins);

        if (!pins.isAllDown()) {
            return new NormalFrame(round + 1, pins);
        }

        return this;
    }

    @Override
    public int round() {
        return round;
    }

    @Override
    public Pins pins() {
        return pins;
    }

}
