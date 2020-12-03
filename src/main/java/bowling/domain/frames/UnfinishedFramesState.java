package bowling.domain.frames;

import bowling.domain.Rolls;

public class UnfinishedFramesState implements FramesState {
    private UnfinishedFramesState() {}

    static UnfinishedFramesState getInstance() {
        return SingletonHelper.instance;
    }

    @Override
    public void update(FramesContext context, Rolls rolls) {
        context.getLast().update(rolls);
        FramesMediator.notify(context);
    }

    private static class SingletonHelper {
        private static final UnfinishedFramesState instance = new UnfinishedFramesState();
    }
}
