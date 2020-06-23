package bowling.domain.frame;

import bowling.domain.state.PinsState;

public class Frame {

    private final int position;
    private final Pins pins;

    private Frame(int position, Pins pins) {
        this.position = position;
        this.pins = pins;
    }

    static Frame first() {
        return new Frame(0, new NormalPins());
    }

    static Frame last() {
        return new Frame(9, FinalPins.newInstance());
    }

    Frame next() {
        return new Frame(this.position + 1, new NormalPins());
    }

    public void play(int numberOfDownPin) {
        this.pins.down(numberOfDownPin);
    }

    public boolean hasTurn() {
        return this.pins.hasTurn();
    }


    public PinsState getPinsState() {
        return this.pins.getPinsState();
    }

}
