package bowling.domain.state;

public interface State {

    public State play(int newHitCount);

    public boolean isEnd();

    public State snapShot();

    public boolean isBonusPlayableState();

    public String getString();
}
