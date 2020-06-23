package bowling.domain.frame;


import bowling.domain.dto.FrameResult;
import bowling.domain.pin.PinCount;
import bowling.domain.state.State;
import bowling.domain.state.StateFactory;

public class NormalFrame extends Frame {

    private State state;
    private final FrameNumber frameNumber;

    private NormalFrame(final FrameNumber frameNumber) {
        this.state = StateFactory.ready();
        this.frameNumber = frameNumber;
    }

    public static NormalFrame ofFirst() {
        return new NormalFrame(FrameNumber.of(FrameNumber.MIN_NUMBER));
    }

    public static NormalFrame newInstance(final FrameNumber frameNumber) {
        return new NormalFrame(frameNumber);
    }

    @Override
    public void bowl(final PinCount hitCount) {
        this.state = this.state.bowl(hitCount);
    }

    @Override
    public Frame initNextFrame() {
        if (this.isFinish()) {
            return createNextFrame();
        }
        return this;
    }

    private boolean isFinish() {
        return this.state.isFinish();
    }

    private Frame createNextFrame() {
        FrameNumber nextNumber = this.frameNumber.increase();

        if (nextNumber.isFinal()) {
            return FinalFrame.newInstance();
        }
        return NormalFrame.newInstance(nextNumber);
    }

    @Override
    void addFrame(final Frames frames) {
        if (isFinish()) {
            frames.add(initNextFrame());
        }
    }

    @Override
    public int getFrameNo() {
        return this.frameNumber.getNo();
    }

    @Override
    public FrameResult getFrameResult() {
        return FrameResult.of(state.getDesc());
    }
}
