package bowling.model.frame;

public class FinalFrame extends Frame {
    private static final int FINAL_FRAME_NUMBER = 10;

    private FallenPin bonusFallenPin;

    public FinalFrame(FrameNumber number, FrameFallenPin fallenPin) {
        super(number, fallenPin);
    }

    private FinalFrame(FrameNumber number, FrameFallenPin fallenPin, int bonusFallenPinCount) {
        super(number, fallenPin);
        this.bonusFallenPin = FallenPin.of(bonusFallenPinCount);
    }

    public FinalFrame(int number, int firstFallenPinCount) {
        super(new FrameNumber(number), FrameFallenPin.first(firstFallenPinCount));
    }

    public FinalFrame(int number, int firstFallenPinCount, int secondFallenPinCount) {
        super(number, firstFallenPinCount, secondFallenPinCount);
    }

    public FinalFrame(int number, int firstFallenPinCount, int secondFallenPinCount, int bonusFallenPinCount) {
        super(number, firstFallenPinCount, secondFallenPinCount);
        this.bonusFallenPin = FallenPin.of(bonusFallenPinCount);
    }

    @Override
    protected void validateNumberRange(FrameNumber number) {
        if (!number.isEqual(FINAL_FRAME_NUMBER)) {
            throw new IllegalArgumentException(String.format("마지막 프레임 번호는 %d 이어야 합니다.", FINAL_FRAME_NUMBER));
        }
    }

    @Override
    public boolean canPlayNext() {
        return bonusFallenPin == null && (fallenPin.isFirst() || isStrikeOrSpare());
    }

    @Override
    public Frame next(int fallenPinCount) {
        if (isFirstAndNotStrike()) {
            return new FinalFrame(number, this.fallenPin.second(fallenPinCount));
        }

        if (isStrikeOrSpare()) {
            return new FinalFrame(number, this.fallenPin, fallenPinCount);
        }

        throw new IllegalArgumentException("마지막 프레임의 다음 번호 프레임을 만들 수 없습니다.");
    }

    @Override
    public FrameNumber nextNumber() {
        if (isFirstAndNotStrike() || isStrikeOrSpare()) {
            return number;
        }
        throw new IllegalArgumentException("마지막 프레임의 다음 번호 프레임을 만들 수 없습니다.");
    }

    @Override
    public boolean isBonusPlay() {
        return bonusFallenPin != null;
    }

    @Override
    public FallenPin getBonusFallenPin() {
        return bonusFallenPin;
    }
}
