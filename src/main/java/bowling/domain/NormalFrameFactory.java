package bowling.domain;

public class NormalFrameFactory implements FrameFatory{
    @Override
    public Frame frame(FrameNumber frameNumber) {
        return new NormalFrame(frameNumber);
    }
}
