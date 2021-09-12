package bowling.domain;

public interface FrameState {
    FrameState bowl(FallenPinCount fallenPinCount);
}
