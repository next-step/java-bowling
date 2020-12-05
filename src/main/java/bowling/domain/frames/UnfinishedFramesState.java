package bowling.domain.frames;

import bowling.domain.Pins;

import static bowling.asset.Const.MAX_FRAME_NO;

public class UnfinishedFramesState implements FramesState {
    private UnfinishedFramesState() {}

    static UnfinishedFramesState getInstance() {
        return SingletonHelper.instance;
    }

    @Override
    public void update(Frames frames, Pins pins) {
        frames.getLast().update(pins);
        FramesState nextState = frames.size() < MAX_FRAME_NO
                ? FinishedFramesState.getInstance()
                : GameOverFramesState.getInstance();
        frames.setState(nextState);
    }

    private static class SingletonHelper {
        private static final UnfinishedFramesState instance = new UnfinishedFramesState();
    }
}
