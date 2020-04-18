package bowling.domain.state;

public interface State {
    State play(PinCount newFelledPin);

    boolean isEndedState();

    String getString();
}
