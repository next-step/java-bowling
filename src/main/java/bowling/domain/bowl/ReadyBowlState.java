package bowling.domain.bowl;

public class ReadyBowlState implements BowlState {
    private ReadyBowlState() {}

    static ReadyBowlState getInstance() {
        return SingletonHelper.instance;
    }

    @Override
    public boolean isPlayable(Bowl bowl, int frameNo) {
        bowl.setState(new FirstBowlState());
        return true;
    }

    private static class SingletonHelper {
        private static final ReadyBowlState instance = new ReadyBowlState();
    }
}
