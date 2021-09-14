package bowling.domain;

public class NormalFrame implements Frame {

    private FrameState state;

    public NormalFrame() {
        this.state = new Ready();
    }

    @Override
    public void bowl(PinCount fallenPinCount) {
        state = state.bowl(fallenPinCount);
    }

    @Override
    public boolean isFinished() {
        return state instanceof Finished;
    }

    @Override
    public Renderer createFrameStateRenderer() {
        return state.toRenderer();
    }
}
