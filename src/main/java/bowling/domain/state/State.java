package bowling.domain.state;

import bowling.domain.PinCount;

public interface State {
    State play(PinCount newFelledPin);

    boolean isEndedState();

    String getString();
}
