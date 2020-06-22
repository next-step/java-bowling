package bowling.domain.frameResult;

public interface FrameResult {
    boolean isCompleted();
    FrameResult bowl(int numberOfHitPin);
}
