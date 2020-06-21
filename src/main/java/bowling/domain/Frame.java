package bowling.domain;

public interface Frame {
    boolean isFinal();
    boolean isCompleted();
    FrameStatuses calculateCurrentStatus();
}
