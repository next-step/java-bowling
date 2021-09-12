package bowling.model.frame;

public abstract class Frame {
    protected FrameNumber number;
    protected FrameResult result;

    protected Frame(FrameNumber number, FrameFallenPin fallenPin, FrameScore score) {
        validateNumberRange(number);

        this.number = number;
        this.result = new FrameResult(fallenPin, score);
    }

    public Frame(int frameNumber, int firstFallenPinCount, int secondFallenPinCount, int score,
                 int remainingPitchingCount) {
        FrameNumber number = new FrameNumber(frameNumber);
        validateNumberRange(number);

        this.number = number;
        this.result = new FrameResult(firstFallenPinCount, secondFallenPinCount, score, remainingPitchingCount);
    }

    public Frame(int frameNumber, int firstFallenPinCount, int score, int remainingPitchingCount) {
        FrameNumber number = new FrameNumber(frameNumber);
        validateNumberRange(number);

        this.number = number;
        this.result = new FrameResult(FrameFallenPin.first(firstFallenPinCount), new FrameScore(score, remainingPitchingCount));
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
        return result.isStrike();
    }

    protected boolean isFirstAndNotStrike() {
        return result.isFirstAndNotStrike();
    }

    public boolean pitchTwice() {
        return result.pitchTwice();
    }

    public boolean isSpare() {
        return result.isSpare();
    }

    public FallenPin firstFallenPin() {
        return result.firstFallenPin();
    }

    public FallenPin secondFallenPin() {
        return result.secondFallenPin();
    }

    public boolean isFrameNumberEqual(Frame frame) {
        return number.equals(frame.number);
    }

    public void calculateRemainingScore(Frame nextFrame) {
        if (!isStrikeOrSpare()) {
            throw new IllegalArgumentException("스트라이크 또는 스페어 점수를 계산할 수 있는 상태가 아닙니다.");
        }

        nextFrame.plusScore(nextFrame.fallenPinCountTotal());
        result.plusScore(nextFrame.fallenPinCountTotal());
    }

    private void plusScore(int other) {
        result.plusScore(other);
    }

    private int fallenPinCountTotal() {
        return result.fallenPinCountTotal();
    }

    public int scoreValue() {
        return result.scoreValue();
    }

    public boolean remainsNextPitching() {
        return result.remainsPitchingCount();
    }

    public void decreaseRemainingPitchingCountOne() {
        result.decreaseRemainingPitchingCountOne();
    }
}
