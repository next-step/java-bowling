package domain;

public class NormalFrame implements Frame{

    private State state;
    private Frame nextFrame;

    private NormalFrame() {
        this.state = new StandBy();
    }

    public static NormalFrame of() {
        return new NormalFrame();
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
}
