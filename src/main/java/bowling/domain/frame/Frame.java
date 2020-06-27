package bowling.domain.frame;

import bowling.domain.FrameResults;
import bowling.domain.FrameScore;

public interface Frame {
    boolean isCompleted();

    FrameResults calculateCurrentResults();

    Frame bowl(int numberOfHitPin);

    Frame next(int numberOfHitPin);

    FrameScore calculateCurrentScore();

    FrameScore calculatePreviousScore();
}
