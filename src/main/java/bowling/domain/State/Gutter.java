package bowling.domain.State;

import bowling.domain.score.Score;
import bowling.domain.score.UnDefinedScore;

public class Gutter implements State {

    public final static String SYMBOL = "-";

    @Override
    public State newState(PinCount pinCount) {
        if (PinCount.GUTTER.isSpare(pinCount)) {
            return new Spare(PinCount.GUTTER, pinCount);
        }
        return new Miss(PinCount.GUTTER, pinCount);
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
