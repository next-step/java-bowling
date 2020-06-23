package bowling.domain.frame;

import bowling.domain.FrameResults;

public interface Frame {
    boolean isCompleted();
    FrameResults calculateCurrentResults();
    Frame bowl(int numberOfHitPin);
}
