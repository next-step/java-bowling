package bowling.domain.frameResult;

import bowling.domain.FrameStatuses;

public interface FrameResult {
    boolean isStrikeResult();
    boolean isCompleted();
    FrameStatuses calculateCurrentStatus();
}

