package bowling.domain.state;

public interface State {
    public State play(int falledPin);
}
