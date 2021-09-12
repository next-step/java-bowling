package bowling.model.frame;

public class FrameScore {
    private Score score;
    private final RemainingPitchingCount remainingPitchingCount;

    private FrameScore(Score score, RemainingPitchingCount remainingPitchingCount) {
        this.score = score;
        this.remainingPitchingCount = remainingPitchingCount;
    }

    public FrameScore(int score, int remainingPitchingCount) {
        this(Score.from(score), new RemainingPitchingCount(remainingPitchingCount));
    }

    public static FrameScore initial(FrameFallenPin fallenPin) {
        int initialFallenPinCount = fallenPin.countTotal();
        return new FrameScore(Score.from(initialFallenPinCount), findRemainingPitchingCount(fallenPin));
    }

    public FrameScore nextFirst(FrameFallenPin firstFallenPin) {
        int firstFallenPinCount = firstFallenPin.firstCount();
        return new FrameScore(score.plusScore(firstFallenPinCount), findRemainingPitchingCount(firstFallenPin));
    }

    public FrameScore nextSecond(FrameFallenPin secondFallenPin) {
        int secondFallenPinCount = secondFallenPin.secondCount();
        return new FrameScore(score.plusScore(secondFallenPinCount), findRemainingPitchingCount(secondFallenPin));
    }

    private static RemainingPitchingCount findRemainingPitchingCount(FrameFallenPin frameFallenPin) {
        if (frameFallenPin.isStrike()) {
            return RemainingPitchingCount.strike();
        }

        if (frameFallenPin.isSpare()) {
            return RemainingPitchingCount.spare();
        }

        if (frameFallenPin.pitchTwice()) {
            return RemainingPitchingCount.secondAndNotSpare();
        }

        return RemainingPitchingCount.firstAndNotStrike();
    }

    public Score score() {
        return score;
    }

    public int scoreValue() {
        return score.value();
    }

    public void plus(int additionalScore) {
        score.plus(additionalScore);
    }

    public boolean remainsPitchingCount() {
        return !remainingPitchingCount.isNoCount();
    }

    public void decreaseRemainingPitchingCountOne() {
        remainingPitchingCount.decrease();
    }

    public RemainingPitchingCount remainingPitchingCount() {
        return remainingPitchingCount;
    }
}
