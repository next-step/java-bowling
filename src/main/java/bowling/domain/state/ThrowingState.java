package bowling.domain.state;

import bowling.domain.Pins;

public interface ThrowingState {

    ThrowingState bowl(Pins pins);

    String symbol();

    boolean isMiss();

    boolean isEnd();
}
