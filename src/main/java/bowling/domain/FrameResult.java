package bowling.domain;

public interface FrameResult {
    boolean isStrikeResult();
    boolean isCompleted();
    boolean isFinalFrame();
    FrameStatus calculateCurrentStatus();
}

