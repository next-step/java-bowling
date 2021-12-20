package bowling.domain.frame;

import bowling.domain.Pitch;
import bowling.domain.state.Start;
import bowling.domain.state.State;

public class NormalFrame extends TemplateFrame {
    private Frame nextFrame;

    private NormalFrame() {
        super();
        this.nextFrame = null;
    }

    private NormalFrame(FrameInfo frameInfo) {
        super(frameInfo, new Start());
        this.nextFrame = null;
    }

    public static Frame first() {
        return new NormalFrame();
    }

    @Override
    public Frame next() {
        if (frameInfo.nextLast()) {
            nextFrame = FinalFrame.create(frameInfo.next());
            return nextFrame;
        }
        nextFrame = new NormalFrame(frameInfo.next());
        return nextFrame;
    }

    @Override
    public void addPitch(Pitch pitch) {
        frameInfo.addPitch(pitch);
    }

    @Override
    public State state() {
        return state;
    }

    @Override
    public boolean isFinal() {
        return false;
    }

    @Override
    public boolean isThirdPitch() {
        return false;
    }
}
