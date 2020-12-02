package bowling.domain.frames;

import bowling.domain.Rolls;

public class GameOverFramesState implements FramesState {
    private GameOverFramesState() {}

    static GameOverFramesState getInstance() {
        return SingletonHelper.instance;
    }

    @Override
    public void update(FramesContext context, Rolls rolls) {}

    private static class SingletonHelper {
        private static final GameOverFramesState instance = new GameOverFramesState();
    }
}
