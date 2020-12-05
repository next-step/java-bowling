package bowling.domain.bowl;

public class GameOverBowlState implements BowlState {
    private GameOverBowlState() {}

    static GameOverBowlState getInstance() {
        return SingletonHelper.instance;
    }

    @Override
    public boolean isPlayable(Bowl context, int frameNo) {
        return false;
    }

    private static class SingletonHelper {
        private static final GameOverBowlState instance = new GameOverBowlState();
    }
}
