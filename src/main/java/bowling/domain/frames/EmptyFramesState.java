package bowling.domain.frames;

import bowling.domain.Pins;
import bowling.domain.frame.Frame;
import bowling.domain.frame.FrameEnum;

public class EmptyFramesState implements FramesState {
    private EmptyFramesState() {}

    static EmptyFramesState getInstance() {
        return SingletonHelper.instance;
    }

    @Override
    public void update(Frames frames, Pins pins) {
        Frame frame = Frame.of(pins);
        frames.addFrame(frame);
        FramesState nextState = frame.getFrameEnum() == FrameEnum.UNFINISHED
                ? UnfinishedFramesState.getInstance()
                : FinishedFramesState.getInstance();
        frames.setState(nextState);
    }

    private static class SingletonHelper {
        private static final EmptyFramesState instance = new EmptyFramesState();
    }
}
