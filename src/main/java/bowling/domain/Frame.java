package bowling.domain;

public interface Frame {
    void bowl(PinCount fallenPinCount);
    boolean isFinished();
    Renderer createFrameStateRenderer();
}
