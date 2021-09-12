package bowling.domain;

import java.util.Objects;

public class Proceeding implements FrameState {

    private final FallenPinCount firstFallenPinCount;

    public Proceeding(FallenPinCount firstFallenPinCount) {
        this.firstFallenPinCount = Objects.requireNonNull(firstFallenPinCount);
    }

    @Override
    public FrameState bowl(FallenPinCount fallenPinCount) {
        if (fallenPinCount.cannotBeSecondBowlOf(firstFallenPinCount)) {
            throw new IllegalArgumentException(String.format("쓰러트린 핀의 수가 유효하지 않습니다. first: %s, second: %s", firstFallenPinCount, fallenPinCount));
        }
        return null;
    }
}
