package domain;

public class NormalFrame implements Frame{

    private int index;
    private State state;
    private Frame nextFrame;

    private NormalFrame(int index) {
        this.index = index;
        this.state = new StandBy();
    }

    public static NormalFrame of(int index) {
        return new NormalFrame(index);
    }

    @Override
    public Frame fillFrame(Pins fallenPins) {
        state = state.update(fallenPins);
        if (state.isClosed()) {
            this.nextFrame = generateNextFrame();
            return nextFrame;
        }
        return this;
    }

    Frame generateNextFrame() {
        if (this.index == 9) {
            return new FinalFrame();
        }
        return new NormalFrame(index + 1);
    }

    public int getIndex() {
        return index;
    }
}
