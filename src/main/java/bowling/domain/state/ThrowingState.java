package bowling.domain.state;

import bowling.domain.Pins;

public interface ThrowingState {
    ThrowingState bowl(Pins pins);

    boolean isEnd();

    String symbol();

    boolean isMiss();
}
