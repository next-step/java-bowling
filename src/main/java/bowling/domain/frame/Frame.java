package bowling.domain.frame;

import bowling.domain.Pins;
import bowling.domain.Score;

public interface Frame {
    void bowl(Pins hitPins);

    boolean isFrameEnd();

    boolean isFinalFrame();

    String symbol();

    Score score();

    Score calculateAdditionalScore(Score previousScore);
}