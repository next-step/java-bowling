package bowling.domain.frames;

import bowling.domain.Frame;
import bowling.domain.Rolls;

public class FinishedFramesState implements FramesState {
    private FinishedFramesState() {}

    static FinishedFramesState getInstance() {
        return SingletonHelper.instance;
    }

    @Override
    public void update(FramesContext context, Rolls rolls) {
        Frame frame = Frame.of(rolls);
        context.addFrame(frame);
        FramesMediator.notify(context, frame);
    }

    private static class SingletonHelper {
        private static final FinishedFramesState instance = new FinishedFramesState();
    }
}
