package bowling.domain.frame;

import bowling.domain.Pins;

abstract class FrameState {
    private final int pinsIndex;

    FrameState(int pinsIndex) {
        this.pinsIndex = pinsIndex;
    }

    FrameState(FrameState state) {
        pinsIndex = state.pinsIndex;
    }

    public int getPinsIndex() {
        return pinsIndex;
    }

    abstract FrameEnum getFrameEnum();

    abstract int getScore(Frame frame, Pins pins);

    abstract boolean hasScore(Frame frame, Pins pins);

    abstract void update(Frame frame, Pins pins);
}
