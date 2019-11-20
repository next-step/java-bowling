package bowling.domain.state;

public abstract class State {

    protected int playCount;
    protected int remainPinCount;

    public abstract State play(int hitCount);

    public abstract boolean isEnd();
}
