package bowling.domain;

public class FinalFrameFactory implements FrameFatory {
    @Override
    public Frame frame(FrameNumber frameNumber) {
        return new FinalFrame();
    }
}
