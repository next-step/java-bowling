package bowling.domain.State;

import bowling.domain.score.Score;
import bowling.domain.score.UnDefinedScore;

public class Gutter implements State {

    public static final String SYMBOL = "-";

    @Override
    public State newState(PinCount secondPinCount) {
        if (secondPinCount.isSpare(PinCount.GUTTER)) {
            return new Spare(PinCount.GUTTER, secondPinCount);
        }
        return new Miss(PinCount.GUTTER, secondPinCount);
    }

    @Override
    public boolean isClosed() {
        return false;
    }

    @Override
    public String stateInString() {
        return SYMBOL;
    }

    @Override
    public Score score() {
        return UnDefinedScore.ofEmpty();
    }

    @Override
    public Score calculatedScore(Score scoreToCalculate) {
        throw new IllegalStateException("점수를 계산 할 수 없습니다.");
    }
}
