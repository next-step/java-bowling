package bowling.model.frame;

public class FinalFrame extends Frame {
    private static final int FINAL_FRAME_NUMBER = 10;

    private FallenPin bonusFallenPin;

    public FinalFrame(FrameNumber number, FrameFallenPin fallenPin, FrameScore score) {
        super(number, fallenPin, score);
    }

    public FinalFrame(FrameNumber number, FrameFallenPin fallenPin, FrameScore score, int bonusFallenPin) {
        super(number, fallenPin, score);
        this.bonusFallenPin = FallenPin.of(bonusFallenPin);
    }

    public FinalFrame(int frameNumber, int firstFallenPinCount, int secondFallenPinCount,
                      int score, int waitingPitchingCount, int bonusFallenPin) {
        super(frameNumber, firstFallenPinCount, secondFallenPinCount, score, waitingPitchingCount);
        this.bonusFallenPin = FallenPin.of(bonusFallenPin);
    }

    public FinalFrame(int frameNumber, int firstFallenPinCount,
                      int score, int waitingPitchingCount, int bonusFallenPin) {
        super(frameNumber, firstFallenPinCount, score, waitingPitchingCount);
        this.bonusFallenPin = FallenPin.of(bonusFallenPin);
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
            FrameFallenPin secondFallenPin = fallenPin.second(fallenPinCount);
            return new FinalFrame(number, secondFallenPin, score.nextSecond(secondFallenPin));
        }

        if (isStrikeOrSpare()) {
            FrameFallenPin firstFallenPin = FrameFallenPin.first(fallenPinCount);
            return new FinalFrame(number, fallenPin, score.nextFirst(firstFallenPin), fallenPinCount);
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
