package bowling.domain;

import bowling.domain.state.ThrowingState;

public interface Frame {
    Frame play(final int round, final int numberOfFallenPins);
    int first();
    int second();
    ThrowingState firstState();
    ThrowingState secondState();
    boolean firstOfFrame();
    boolean endFrame();
}
