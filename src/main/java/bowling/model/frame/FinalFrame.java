package bowling.model.frame;

public class FinalFrame extends Frame {
    private static final int FINAL_FRAME_NUMBER = 10;

    private FallenPin bonusFallenPin;

    public FinalFrame(FrameNumber number, FrameFallenPin fallenPin, FrameScore score) {
        super(number, fallenPin, score);
    }

    public FinalFrame(FrameNumber number, FrameFallenPin fallenPin, FrameScore score, int bonusFallenPin) {
        this(number, fallenPin, score);
        this.bonusFallenPin = FallenPin.from(bonusFallenPin);
    }

    public FinalFrame(int frameNumber, int firstFallenPinCount, int secondFallenPinCount, int score,
                      int remainingPitchingCount, int bonusFallenPin) {
        super(frameNumber, firstFallenPinCount, secondFallenPinCount, score, remainingPitchingCount);
        this.bonusFallenPin = FallenPin.from(bonusFallenPin);
    }

    public FinalFrame(int frameNumber, int firstFallenPinCount, int score, int remainingPitchingCount,
                      int bonusFallenPin) {
        super(frameNumber, firstFallenPinCount, score, remainingPitchingCount);
        this.bonusFallenPin = FallenPin.from(bonusFallenPin);
    }

    @Override
    protected void validateNumberRange(FrameNumber number) {
        if (!number.isEqual(FINAL_FRAME_NUMBER)) {
            throw new IllegalArgumentException(String.format("마지막 프레임 번호는 %d 이어야 합니다.", FINAL_FRAME_NUMBER));
        }
    }

    @Override
    public boolean canPlayNext() {
        return bonusFallenPin == null && (result.isFirstFallenPin() || isStrikeOrSpare());
    }

    @Override
    public Frame next(int fallenPinCount) {
        if (isFirstAndNotStrike()) {
            FrameFallenPin secondFallenPin = result.secondFallenPin(fallenPinCount);
            return new FinalFrame(number, secondFallenPin, result.nextSecondScore(secondFallenPin));
        }

        if (isStrikeOrSpare()) {
            FrameFallenPin firstFallenPin = FrameFallenPin.first(fallenPinCount);
            return new FinalFrame(number, result.fallenPin(), result.nextFirstScore(firstFallenPin), fallenPinCount);
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
    public FallenPin bonusFallenPin() {
        return bonusFallenPin;
    }

    @Override
    public boolean isPitchOver() {
        return (pitchTwice() && !isSpare()) || (isStrikeOrSpare() && isBonusPlay());
    }
}
