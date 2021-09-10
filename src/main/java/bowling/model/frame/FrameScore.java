package bowling.model.frame;

public class FrameScore {
    private final Score score;
    private final WaitingPitchingCount count;

    private FrameScore(Score score, WaitingPitchingCount count) {
        this.score = score;
        this.count = count;
    }

    public FrameScore(int score, int waitingPitchingCount) {
        this.score = Score.of(score);
        this.count = new WaitingPitchingCount(waitingPitchingCount);
    }

    public static FrameScore ofInitialFallenPin(FrameFallenPin fallenPin) {
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

    private static WaitingPitchingCount findWaitingPitchingCountOfFirstFallenPin(FrameFallenPin fallenPin) {
        if (fallenPin.isStrike()) {
            return WaitingPitchingCount.ofStrike();
        }
        return WaitingPitchingCount.oneCount();
    }

    private WaitingPitchingCount findWaitingPitchingCountOfSecondFallenPin(FrameFallenPin secondFallenPin) {
        if (secondFallenPin.isSpare()) {
            return WaitingPitchingCount.ofSpare();
        }
        return WaitingPitchingCount.noCount();
    }

    public int waitingPitchingCount() {
        return count.count();
    }
}
