package bowling.domain.FrameResult;

import bowling.domain.FrameStatus;

public interface FrameResult {
    boolean isStrikeResult();
    boolean isCompleted();
    boolean isFinalFrame();
    FrameStatus calculateCurrentStatus();
}

