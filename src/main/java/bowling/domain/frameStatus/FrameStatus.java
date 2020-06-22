package bowling.domain.frameStatus;

public interface FrameStatus {
    boolean isCompleted();
    FrameStatus bowl(int numberOfHitPin);
}
