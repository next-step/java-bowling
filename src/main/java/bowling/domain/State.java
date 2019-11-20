package bowling.domain;

public abstract class State {

    protected int remainPinCount;

    public abstract State play(int hitCount);
}
