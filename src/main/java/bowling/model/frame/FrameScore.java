package bowling.model.frame;

public class FrameScore {
    private final Score score;
    private final WaitingPitchingCount waitingPitchingCount;

    private FrameScore(Score score, WaitingPitchingCount waitingPitchingCount) {
        this.score = score;
        this.waitingPitchingCount = waitingPitchingCount;
    }

    public FrameScore(int score, int waitingPitchingCount) {
        this.score = Score.of(score);
        this.waitingPitchingCount = new WaitingPitchingCount(waitingPitchingCount);
    }

    public static FrameScore initial(FrameFallenPin fallenPin) {
        int initialFallenPinCount = fallenPin.countTotal();
        return new FrameScore(Score.of(initialFallenPinCount), findWaitingPitchingCountOfFirstFallenPin(fallenPin));
    }

    public FrameScore nextFirst(FrameFallenPin firstFallenPin) {
        int firstFallenPinCount = firstFallenPin.countTotal();
        return new FrameScore(score.plus(firstFallenPinCount), findWaitingPitchingCountOfFirstFallenPin(firstFallenPin));
    }

    public FrameScore nextSecond(FrameFallenPin fallenPinTotal) {
        int fallenPinCountTotal = fallenPinTotal.countTotal();
        return new FrameScore(score.plus(fallenPinCountTotal), findWaitingPitchingCountOfSecondFallenPin(fallenPinTotal));
    }

    private static WaitingPitchingCount findWaitingPitchingCountOfFirstFallenPin(FrameFallenPin firstFallenPin) {
        if (firstFallenPin.isStrike()) {
            return WaitingPitchingCount.ofStrike();
        }
        return WaitingPitchingCount.ofFirstAndNotStrike();
    }

    private WaitingPitchingCount findWaitingPitchingCountOfSecondFallenPin(FrameFallenPin secondFallenPin) {
        if (secondFallenPin.isSpare()) {
            return WaitingPitchingCount.ofSpare();
        }
        return WaitingPitchingCount.ofSecondAndNotSpare();
    }

    public int waitingPitchingCount() {
        return waitingPitchingCount.count();
    }
}
