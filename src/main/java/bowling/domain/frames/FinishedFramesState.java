package bowling.domain.frames;

import bowling.domain.Frame;
import bowling.domain.FrameEnum;
import bowling.domain.Rolls;

import static bowling.asset.Const.MAX_FRAME_NO;

public class FinishedFramesState implements FramesState {
    private FinishedFramesState() {}

    static FinishedFramesState getInstance() {
        return SingletonHelper.instance;
    }

    @Override
    public void update(FramesContext context, Rolls rolls) {
        Frame frame = Frame.of(rolls);
        context.addFrame(frame);
        FramesState nextState = frame.getFrameEnum() == FrameEnum.UNFINISHED
                ? UnfinishedFramesState.getInstance()
                : context.size() < MAX_FRAME_NO
                ? FinishedFramesState.getInstance()
                : GameOverFramesState.getInstance();
        context.setState(nextState);
    }

    private static class SingletonHelper {
        private static final FinishedFramesState instance = new FinishedFramesState();
    }
}
