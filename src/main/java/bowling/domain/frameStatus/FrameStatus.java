package bowling.domain.frameStatus;

import bowling.domain.FrameResults;

public interface FrameStatus {
    boolean isCompleted();
    FrameStatus bowl(int numberOfHitPin);
    FrameResults calculateCurrentResult();
}
