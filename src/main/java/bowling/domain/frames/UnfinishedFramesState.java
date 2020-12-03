package bowling.domain.frames;

import bowling.domain.Rolls;

import static bowling.asset.Const.MAX_FRAME_NO;

public class UnfinishedFramesState implements FramesState {
    private UnfinishedFramesState() {}

    static UnfinishedFramesState getInstance() {
        return SingletonHelper.instance;
    }

    @Override
    public void update(FramesContext context, Rolls rolls) {
        context.getLast().update(rolls);
        FramesState nextState = context.size() < MAX_FRAME_NO
                ? FinishedFramesState.getInstance()
                : GameOverFramesState.getInstance();
        context.setState(nextState);
    }

    private static class SingletonHelper {
        private static final UnfinishedFramesState instance = new UnfinishedFramesState();
    }
}
