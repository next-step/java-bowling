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
        return new FrameScore(Score.from(initialFallenPinCount), findRemainingPitchingCountOfFirstFallenPin(fallenPin));
    }

    public FrameScore nextFirst(FrameFallenPin firstFallenPin) {
        int firstFallenPinCount = firstFallenPin.firstCount();
        return new FrameScore(score.plusScore(firstFallenPinCount), findRemainingPitchingCountOfFirstFallenPin(firstFallenPin));
    }

    public FrameScore nextSecond(FrameFallenPin fallenPinTotal) {
        int secondFallenPinCount = fallenPinTotal.secondCount();
        return new FrameScore(score.plusScore(secondFallenPinCount), findRemainingPitchingCountOfSecondFallenPin(fallenPinTotal));
    }

    private static RemainingPitchingCount findRemainingPitchingCountOfFirstFallenPin(FrameFallenPin firstFallenPin) {
        if (firstFallenPin.isStrike()) {
            return RemainingPitchingCount.strike();
        }
        return RemainingPitchingCount.firstAndNotStrike();
    }

    private RemainingPitchingCount
    findRemainingPitchingCountOfSecondFallenPin(FrameFallenPin secondFallenPin) {
        if (secondFallenPin.isSpare()) {
            return RemainingPitchingCount.spare();
        }
        return RemainingPitchingCount.secondAndNotSpare();
    }

    public int remainingPitchingCount() {
        return remainingPitchingCount.count();
    }

    public Score score() {
        return score;
    }

    public int scoreValue() {
        return score.value();
    }

    public void plus(int other) {
        score.plus(other);
    }

    public boolean remainsPitchingCount() {
        return !remainingPitchingCount.isNoCount();
    }

    public void decreaseRemainingPitchingCountOne() {
        remainingPitchingCount.decreaseOne();
    }
}
