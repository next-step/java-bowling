package bowling.domain.state;

public interface State {
    public State play(int newFelledPin);

    boolean isEndedState();

    String getString();
}
