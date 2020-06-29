package bowling.domain;

public class NormalFrame implements Frame {
    private static final int MAX_TRY_COUNT = 2;

    private Pins pins;
    private int trying;

    public NormalFrame(Pins pins, int trying) {
        this.pins = pins;
        this.trying = trying;
    }

    public static Frame init() {
        return new NormalFrame(Pins.init(), 0);
    }

    public void bowl(int downPin) {
        pins.bowl(downPin);
        trying++;
    }

    public boolean isLastTryAtFrame() {
        return trying == MAX_TRY_COUNT;
    }

    @Override
    public Frame next() {
        return new NormalFrame(Pins.init(), 0);
    }


    @Override
    public String toString() {
        return "NormalFrame{" +
                "pins=" + pins.getDownPin() +
                ", trying=" + trying +
                '}';
    }
}
