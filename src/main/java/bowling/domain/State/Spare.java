package bowling.domain.State;

import bowling.domain.score.Score;
import bowling.domain.score.UnFinishedScore;

public class Spare implements State {

    public final static String SYMBOL = "|/";

    private final PinCounts pinCounts;

    public Spare(PinCounts pinCounts) {
        if (!pinCounts.isSpare()) {
            throw new IllegalArgumentException("spare 가 아닌 PinCounts입니다.");
        }
        this.pinCounts = pinCounts;
    }

    @Override
    public State newState(PinCount pinCount) {
        throw new IllegalStateException("이미 종료된 상태입니다.");
    }

    @Override
    public boolean isClosed() {
        return true;
    }

    @Override
    public String stateInString() {
        if (pinCounts.firstPinCount().isGutter()) {
            return Gutter.SYMBOL + SYMBOL;
        }
        return pinCounts.firstPinCount().countInString() + SYMBOL;
    }

    @Override
    public Score score() {
        return UnFinishedScore.ofSpare(pinCounts.totalCount());
    }

    @Override
    public Score calculatedScore(Score scoreToCalculate) {
        Score finalScore = scoreToCalculate;
        if (finalScore.isNecessaryToCalculateMore()) {
            finalScore = finalScore.calculatedScore(pinCounts.firstPinCount().count());
        }
        if (finalScore.isNecessaryToCalculateMore()) {
            finalScore = finalScore.calculatedScore(pinCounts.secondPinCount().count());
        }
        return finalScore;
    }
}
