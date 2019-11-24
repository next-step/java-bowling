package bowling.domain.state;

public interface State {

    int INIT_PIT_COUNT = 10;

    public State play(int newHitCount);

    public boolean isEnd();

    public State snapShot();

    public boolean isBonusPlayableState();

    public String getString();
}
