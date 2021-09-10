package bowling.model.frame;

public class NormalFrame extends Frame {
    private static final int MIN_NORMAL_NUMBER = 1;
    private static final int MAX_NORMAL_NUMBER = 9;

    public NormalFrame(FrameNumber number, FrameFallenPin fallenPin, FrameScore score) {
        super(number, fallenPin, score);
    }

    public NormalFrame(int frameNumber, int firstFallenPinCount, int secondFallenPinCount,
                       int score, int waitingPitchingCount) {
        super(frameNumber, firstFallenPinCount, secondFallenPinCount, score, waitingPitchingCount);
    }

    public NormalFrame(int frameNumber, int firstFallenPinCount, int score, int waitingPitchingCount) {
        super(frameNumber, firstFallenPinCount, score, waitingPitchingCount);
    }

    private boolean isNextFinalFrame() {
        return needNextNumber() && number.isNextFinal();
    }

    private boolean needNextNumber() {
        return isStrike() || pitchTwice();
    }

    @Override
    protected void validateNumberRange(FrameNumber number) {
        if (number.isUnder(MIN_NORMAL_NUMBER) || number.isOver(MAX_NORMAL_NUMBER)) {
            throw new IllegalArgumentException(String.format("노말 프레임 번호는 %d 이상 %d 이하 이어야 합니다.",
                    MIN_NORMAL_NUMBER, MAX_NORMAL_NUMBER));
        }
    }

    @Override
    public boolean canPlayNext() {
        return (fallenPin.isFirst() && !isStrike()) || number.canMakeNext();
    }

    @Override
    public Frame next(int fallenPinCount) {
        if (isNextFinalFrame()) {
            FrameFallenPin firstFallenPin = FrameFallenPin.first(fallenPinCount);
            return new FinalFrame(number.next(), firstFallenPin, score.ofFirstFallenPin(firstFallenPin));
        }

        if (needNextNumber()) {
            FrameFallenPin firstFallenPin = FrameFallenPin.first(fallenPinCount);
            return new NormalFrame(number.next(), FrameFallenPin.first(fallenPinCount), score.ofFirstFallenPin(firstFallenPin));
        }

        FrameFallenPin secondFallenPin = fallenPin.second(fallenPinCount);
        return new NormalFrame(number, secondFallenPin, score.ofSecondFallenPin(secondFallenPin));
    }

    @Override
    public FrameNumber nextNumber() {
        if (isNextFinalFrame() || needNextNumber()) {
            return number.next();
        }
        return number;
    }

    @Override
    public boolean isBonusPlay() {
        return false;
    }

    @Override
    public FallenPin getBonusFallenPin() {
        return null;
    }
}
