package bowling.domain.frames;

import bowling.domain.Pins;
import bowling.domain.frame.Frame;
import bowling.domain.frame.FrameEnum;

import static bowling.asset.Const.MAX_FRAME_NO;

public class FinishedFramesState implements FramesState {
    private FinishedFramesState() {}

    static FinishedFramesState getInstance() {
        return SingletonHelper.instance;
    }

    @Override
    public void update(Frames frames, Pins pins) {
        Frame frame = Frame.of(pins);
        frames.addFrame(frame);
        FramesState nextState = frame.getFrameEnum() == FrameEnum.UNFINISHED
                ? UnfinishedFramesState.getInstance()
                : frames.size() < MAX_FRAME_NO
                ? FinishedFramesState.getInstance()
                : GameOverFramesState.getInstance();
        frames.setState(nextState);
    }

    private static class SingletonHelper {
        private static final FinishedFramesState instance = new FinishedFramesState();
    }
}
