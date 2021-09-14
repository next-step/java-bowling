package bowling.domain;

import java.util.Objects;

public class Finished implements FrameState {

    private final PinCount firstFallenPinCount;
    private final PinCount secondFallenPinCount;

    public Finished(PinCount firstFallenPinCount, PinCount secondFallenPinCount) {
        validateSecondFallenPinCount(firstFallenPinCount, secondFallenPinCount);
        this.firstFallenPinCount = Objects.requireNonNull(firstFallenPinCount);
        this.secondFallenPinCount = Objects.requireNonNull(secondFallenPinCount);
    }

    private void validateSecondFallenPinCount(PinCount firstFallenPinCount, PinCount secondFallenPinCount) {
        if (secondFallenPinCount.over(firstFallenPinCount.leftPinCount())) {
            throw new IllegalArgumentException(String.format("쓰러트린 핀의 수가 유효하지 않습니다. first: %s, second: %s", firstFallenPinCount, secondFallenPinCount));
        }
    }

    public static Finished strike() {
        return new Finished(PinCount.TEN, PinCount.ZERO);
    }

    public boolean isStrike() {
        return firstFallenPinCount.equals(PinCount.TEN);
    }

    public boolean isSpare() {
        return !isStrike() && firstFallenPinCount.leftPinCount().equals(secondFallenPinCount);
    }

    public boolean isMiss() {
        return !isStrike() && !isStrike();
    }

    @Override
    public FrameState bowl(PinCount pinCount) {
        throw new IllegalStateException("종료된 프레임입니다.");
    }

    @Override
    public Renderer toRenderer() {
        if (this.isStrike()) {
            return FrameStateRenderer.strike();
        }
        if (this.isSpare()) {
            return FrameStateRenderer.spare(firstFallenPinCount);
        }
        return FrameStateRenderer.miss(firstFallenPinCount, secondFallenPinCount);
    }
}
