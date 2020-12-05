package bowling.domain.frames;

import bowling.domain.Pins;

public class GameOverFramesState implements FramesState {
    private GameOverFramesState() {}

    static GameOverFramesState getInstance() {
        return SingletonHelper.instance;
    }

    @Override
    public void update(Frames context, Pins pins) {}

    private static class SingletonHelper {
        private static final GameOverFramesState instance = new GameOverFramesState();
    }
}
