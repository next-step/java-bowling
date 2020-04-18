package bowling.domain.state;

import bowling.domain.PinCount;

public interface State {
    public State play(int newFelledPin);

    boolean isEndedState();

    String getString();
}
