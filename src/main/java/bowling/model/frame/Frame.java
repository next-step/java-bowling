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

    public Frame(int frameNumber, int firstFallenPinCount, int secondFallenPinCount, int score,
                 int waitingPitchingCount) {
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

    public abstract FallenPin bonusFallenPin();

    public static Frame initial(int fallenPinCount) {
        FrameFallenPin initialFallenPin = FrameFallenPin.first(fallenPinCount);
        return new NormalFrame(FrameNumber.initial(), initialFallenPin, FrameScore.initial(initialFallenPin));
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

    protected boolean pitchOnce() {
        return fallenPin.pitchOnce();
    }

    public boolean isSpare() {
        return fallenPin.isSpare();
    }

    public FrameFallenPin fallenPin() {
        return fallenPin;
    }

    public FallenPin firstFallenPin() {
        return fallenPin.first();
    }

    public FallenPin secondFallenPin() {
        return fallenPin.second();
    }

    public boolean isFrameNumberEqual(Frame frame) {
        return number.equals(frame.number);
    }

    public void calculateStrikeOrSpareScore(Frame nextFrame) {
        if (!isStrikeOrSpare()) {
            throw new IllegalArgumentException("스트라이크 또는 스페어 점수를 계산할 수 있는 상태가 아닙니다.");
        }

        nextFrame.plusScore(nextFrame.fallenPinCountTotal());
        score.plus(nextFrame.fallenPinCountTotal());
        score.makeNoWaitingPitching();
    }

    private void plusScore(int other) {
        score.plus(other);
    }

    private int fallenPinCountTotal() {
        return fallenPin.countTotal();
    }

    public int scoreValue() {
        return score.scoreValue();
    }
}
