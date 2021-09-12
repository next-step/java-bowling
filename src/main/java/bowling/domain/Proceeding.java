package bowling.domain;

import bowling.FrameStateRenderer;

import java.util.Objects;

public class Proceeding implements FrameState {

    private final PinCount firstFallenPinCount;

    public Proceeding(PinCount firstFallenPinCount) {
        this.firstFallenPinCount = Objects.requireNonNull(firstFallenPinCount);
    }

    @Override
    public FrameState bowl(PinCount secondFallenPinCount) {
        return new Finished(firstFallenPinCount, secondFallenPinCount);
    }

    @Override
    public FrameStateRenderer toRenderer() {
        return FrameStateRenderer.of(firstFallenPinCount);
    }

}
