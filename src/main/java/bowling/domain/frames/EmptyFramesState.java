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
    public void update(Frames context, Pins pins) {
        Frame frame = Frame.of(pins);
        context.addFrame(frame);
        FramesState nextState = frame.getFrameEnum() == FrameEnum.UNFINISHED
                ? UnfinishedFramesState.getInstance()
                : FinishedFramesState.getInstance();
        context.setState(nextState);
    }

    private static class SingletonHelper {
        private static final EmptyFramesState instance = new EmptyFramesState();
    }
}
