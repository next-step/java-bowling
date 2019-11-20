package bowling.domain.state;

public abstract class State {

    public static final int INIT_PIT_COUNT = 10;

    protected int playCount;
    protected int hitCount;

    public abstract State play(int newHitCount);

    public abstract boolean isEnd();

    public int getHitCount() {
        return hitCount;
    }

    public int getPlayCount() {
        return playCount;
    }

    public abstract State snapShot();
}
