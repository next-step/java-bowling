package bowling.domain.frames;

import bowling.domain.Frame;
import bowling.domain.FrameEnum;
import bowling.domain.Rolls;

public class EmptyFramesState implements FramesState {
    private EmptyFramesState() {}

    static EmptyFramesState getInstance() {
        return SingletonHelper.instance;
    }

    @Override
    public void update(FramesContext context, Rolls rolls) {
        Frame frame = Frame.of(rolls);
        context.addFrame(frame);
        if (frame.getFrameEnum() == FrameEnum.UNFINISHED) {
            context.setState(UnfinishedFramesState.getInstance());
            return;
        }
        context.setState(FinishedFramesState.getInstance());
    }

    private static class SingletonHelper {
        private static final EmptyFramesState instance = new EmptyFramesState();
    }
}
