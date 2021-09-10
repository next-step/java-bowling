package bowling.model.frame;

public abstract class Frame {
    protected FrameNumber number;
    protected FrameFallenPin fallenPin;
    protected FrameScore score;

    protected Frame(FrameNumber number, FrameFallenPin fallenPin, FrameScore score) {
        validateNumberRange(number);

        this.number = number;
        this.fallenPin = fallenPin;
        this.score = score;
    }

    public Frame(int frameNumber, int firstFallenPinCount, int secondFallenPinCount,
                 int score, int waitingPitchingCount) {
        FrameNumber number = new FrameNumber(frameNumber);
        validateNumberRange(number);

        this.number = number;
        this.fallenPin = new FrameFallenPin(firstFallenPinCount, secondFallenPinCount);
        this.score = new FrameScore(score, waitingPitchingCount);
    }

    public Frame(int frameNumber, int firstFallenPinCount, int score, int waitingPitchingCount) {
        FrameNumber number = new FrameNumber(frameNumber);
        validateNumberRange(number);

        this.number = number;
        this.fallenPin = FrameFallenPin.first(firstFallenPinCount);
        this.score = new FrameScore(score, waitingPitchingCount);
    }

    protected abstract void validateNumberRange(FrameNumber number);

    public abstract boolean canPlayNext();

    public abstract Frame next(int fallenPinCount);

    public abstract FrameNumber nextNumber();

    public abstract boolean isBonusPlay();

    public abstract FallenPin getBonusFallenPin();

    public static Frame initial(int fallenPinCount) {
        FrameFallenPin initialFallenPin = FrameFallenPin.first(fallenPinCount);
        return new NormalFrame(FrameNumber.initial(), initialFallenPin, FrameScore.ofInitialFallenPin(initialFallenPin));
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
