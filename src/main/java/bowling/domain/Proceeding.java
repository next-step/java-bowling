package bowling.domain;

import java.util.Objects;

public class Proceeding implements FrameState {

    private final FallenPinCount firstFallenPinCount;

    public Proceeding(FallenPinCount firstFallenPinCount) {
        this.firstFallenPinCount = Objects.requireNonNull(firstFallenPinCount);
    }

    @Override
    public FrameState bowl(FallenPinCount fallenPinCount) {
        if (firstFallenPinCount.isSparedWith(fallenPinCount)) {
            return new Spare();
        }
        return new Miss();
    }
}
