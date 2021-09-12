package bowling.domain;

import java.util.Objects;

public class Proceeding implements FrameState {

    private final PinCount firstFallenPinCount;

    public Proceeding(PinCount firstFallenPinCount) {
        this.firstFallenPinCount = Objects.requireNonNull(firstFallenPinCount);
    }

    @Override
    public FrameState bowl(PinCount secondFallenPinCount) {
        PinCount leftPinCount = firstFallenPinCount.leftPinCount();
        validateSecondFallenPinCount(leftPinCount, secondFallenPinCount);
        if (leftPinCount.equals(secondFallenPinCount)) {
            return new Spare();
        }
        return new Miss();
    }

    private void validateSecondFallenPinCount(PinCount leftPinCount, PinCount secondFallenPinCount) {
        if (leftPinCount.lessThan(secondFallenPinCount)) {
            throw new IllegalArgumentException(String.format("쓰러트린 핀의 수가 유효하지 않습니다. first: %s, second: %s", firstFallenPinCount, secondFallenPinCount));
        }
    }
}
