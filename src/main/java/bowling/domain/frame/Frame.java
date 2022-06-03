package bowling.domain.frame;

import bowling.domain.Pins;
import bowling.domain.Score;

public interface Frame {
    Frame bowl(Pins hitPins);

    boolean isFrameEnd();

    boolean isFinalFrame();

    FrameNumber frameNumber();

    String symbol();

    Score score();

    Score calculateAdditionalScore(Score previousScore);
}