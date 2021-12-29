package bowling.domain.state;

import bowling.domain.Pins;
import bowling.domain.Score;

public interface ThrowingState {
    ThrowingState bowl(Pins pins);

    boolean isEnd();

    String symbol();

    boolean isMiss();

    Score score();

    Score scoreAfter(Score previousScore);
}
