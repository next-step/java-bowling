package bowling.domain.play;

public class GameOverPlayState implements PlayState {
    private GameOverPlayState() {}

    static GameOverPlayState getInstance() {
        return SingletonHelper.instance;
    }

    @Override
    public boolean isContinue(PlayStatus context, int frameNo) {
        return false;
    }

    private static class SingletonHelper {
        private static final GameOverPlayState instance = new GameOverPlayState();
    }
}
