package bowling.domain.state;

import bowling.domain.Pins;
import bowling.domain.Score;

public interface FrameState {
    FrameState bowl(Pins hitPins);

    boolean isEnd();

    String symbol();

    Score score();

    Score calculateAdditionalScore(Score previousScore);
}