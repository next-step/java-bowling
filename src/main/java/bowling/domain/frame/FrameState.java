package bowling.domain.frame;

abstract class FrameState {
    private final Pins pins;
    private final int pinsIndex;

    FrameState(Pins pins, int pinsIndex) {
        this.pins = pins;
        this.pinsIndex = pinsIndex;
    }

    FrameState(FrameState state) {
        pins = state.pins;
        pinsIndex = state.pinsIndex;
    }

    Pins getPins() {
        return pins;
    }

    void addPin(Pin pin) {
        pins.add(pin);
    }

    boolean hasScore() {
        return pinsIndex + getOffset() <= pins.size();
    }

    int getCountOfDownPins() {
        return pins.sum(pinsIndex, getOffset());
    }

    abstract int getOffset();

    abstract FrameEnum getFrameEnum();

    abstract void updateState(Frame frame);
}
