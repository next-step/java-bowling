package bowling.domain;

import bowling.domain.interfaces.State;

public class NormalFrame {

    private Pins pins = new Pins();
    private State state = new FirstPitch();


    public NormalFrame(int frameCount) {
        this.frameCount = frameCount;
        state = new FirstPitch();
    }

    @Override
    public List<Pins> getPinsList() {
        return Collections.singletonList(state.getPins());
    }

    public Frame bowl(int count) {
        state = state.bowl(count);

        if (!state.getCondition().isFinished()) {
            return this;
        }

        if (frameCount >= NORMAL_FRAME_COUNT) {
            nextFrame = new FinalFrame();
            return nextFrame;
        }
        nextFrame = new NormalFrame(frameCount + 1);
        return nextFrame;
    }

    @Override
    public boolean isGameEnd() {
        return false;
    }

    @Override
    public Frame getNextFrame() {
        return nextFrame;
    }
}
