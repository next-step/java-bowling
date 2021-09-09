package bowling.model.frame;

public abstract class Frame {
    protected FrameNumber number;
    protected FrameFallenPin fallenPin;

    protected Frame(FrameNumber number, FrameFallenPin fallenPin) {
        validateNumberRange(number);

        this.number = number;
        this.fallenPin = fallenPin;
    }

    public Frame(int number, int firstFallenPinCount, int secondFallenPinCount) {
        FrameNumber frameNumber = new FrameNumber(number);
        validateNumberRange(frameNumber);

        this.number = new FrameNumber(number);
        this.fallenPin = new FrameFallenPin(FallenPin.of(firstFallenPinCount), FallenPin.of(secondFallenPinCount));
    }

    protected abstract void validateNumberRange(FrameNumber number);

    public abstract boolean canPlayNext();

    public abstract Frame next(int fallenPinCount);

    public abstract FrameNumber nextNumber();

    public abstract boolean isBonusPlay();

    public abstract FallenPin getBonusFallenPin();

    public static Frame first(int fallenPinCount) {
        return new NormalFrame(FrameNumber.first(), FrameFallenPin.first(fallenPinCount));
    }

    protected boolean isStrikeOrSpare() {
        return isStrike() || isSpare();
    }

    protected boolean isStrike() {
        return fallenPin.isStrike();
    }

    protected boolean isFirstAndNotStrike() {
        return fallenPin.isFirst() && !isStrike();
    }

    protected boolean pitchTwice() {
        return fallenPin.pitchTwice();
    }

    private boolean isSpare() {
        return fallenPin.isSpare();
    }

    public FrameFallenPin getFallenPin() {
        return fallenPin;
    }

    public FallenPin getFirstFallenPin() {
        return fallenPin.getFirst();
    }

    public FallenPin getSecondFallenPin() {
        return fallenPin.getSecond();
    }

    public boolean isFrameNumberEqual(Frame frame) {
        return number.equals(frame.number);
    }
}
