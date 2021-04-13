package bowling.domain.State;

import bowling.domain.score.Score;
import bowling.domain.score.UnDefinedScore;
import bowling.domain.frame.PinCount;

public class Gutter implements State {

    public final static String SYMBOL = "-";

    @Override
    public State newState(PinCount pinCount) {
        return new Miss(new PinCount(0), pinCount);
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
        return new UnDefinedScore(0);
    }

    @Override
    public Score calculatedScore(Score scoreToCalculate) {
        throw new IllegalStateException("점수를 계산 할 수 없습니다.");
    }
}
