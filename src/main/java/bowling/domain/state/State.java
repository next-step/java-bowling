package bowling.domain.state;

public abstract class State {

    public static final int INIT_PIT_COUNT = 10;

    protected int playCount;
    protected int remainPinCount;

    public abstract State play(int hitCount);

    public abstract boolean isEnd();

    public int getHitCount() {
        return INIT_PIT_COUNT - remainPinCount;
    }
}
