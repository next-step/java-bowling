package bowling.model.frame;

public class FrameResult {
    private final FrameFallenPin fallenPin;
    private final FrameScore score;

    public FrameResult(FrameFallenPin fallenPin, FrameScore score) {
        this.fallenPin = fallenPin;
        this.score = score;
    }

    public FrameResult(int firstFallenPinCount, int secondFallenPinCount, int score, int remainingPitchingCount) {
        this.fallenPin = new FrameFallenPin(firstFallenPinCount, secondFallenPinCount);
        this.score = new FrameScore(score, remainingPitchingCount);
    }

    public boolean isStrike() {
        return fallenPin.isStrike();
    }

    public boolean isFirstAndNotStrike() {
        return fallenPin.isFirst() && !isStrike();
    }

    public boolean pitchTwice() {
        return fallenPin.pitchTwice();
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

    public void plusScore(int other) {
        score.plus(other);
    }

    public int fallenPinCountTotal() {
        return fallenPin.countTotal();
    }

    public int scoreValue() {
        return score.scoreValue();
    }

    public Score score() {
        return score.score();
    }

    public boolean remainsPitchingCount() {
        return score.remainsPitchingCount();
    }

    public void decreaseRemainingPitchingCountOne() {
        score.decreaseRemainingPitchingCountOne();
    }

    public boolean isFirstFallenPin() {
        return fallenPin.isFirst();
    }

    public FrameScore nextSecondScore(FrameFallenPin fallenPinTotal) {
        return score.nextSecond(fallenPinTotal);
    }

    public FrameScore nextFirstScore(FrameFallenPin firstFallenPin) {
        return score.nextFirst(firstFallenPin);
    }

    public FrameFallenPin secondFallenPin(int secondFallenPinCount) {
        return fallenPin.second(secondFallenPinCount);
    }
}
