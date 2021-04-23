package bowling.domain.State;

import bowling.domain.score.FinishedScore;
import bowling.domain.score.Score;

public class Miss implements State {

    public final static String SEPARATOR_SYMBOL = "|";

    private final PinCounts pinCounts;

    public Miss(PinCounts pinCounts) {
        if (pinCounts.isSpare()) {
            throw new IllegalArgumentException("miss 가 아닌 PinCounts입니다.");
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
        String firstPinCountInString = pinCounts.firstPinCount().countInString();
        if (pinCounts.firstPinCount().isGutter()) {
            firstPinCountInString = Gutter.SYMBOL;
        }

        String secondPinCountInString = pinCounts.secondPinCount().countInString();
        if (pinCounts.secondPinCount().isGutter()) {
            secondPinCountInString = Gutter.SYMBOL;
        }

        return firstPinCountInString + SEPARATOR_SYMBOL + secondPinCountInString;
    }

    @Override
    public Score score() {
        return FinishedScore.of(pinCounts.totalCount());
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
