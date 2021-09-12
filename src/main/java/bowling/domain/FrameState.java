package bowling.domain;

public interface FrameState {
    FrameState bowl(PinCount pinCount);
}
