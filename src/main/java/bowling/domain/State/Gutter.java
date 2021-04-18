package bowling.domain.State;

import bowling.domain.score.Score;
import bowling.domain.score.UnDefinedScore;

public class Gutter implements State {

    public final static String SYMBOL = "-";

    private final static int PIN_COUNT = 0;

    @Override
    public State newState(PinCount pinCount) {
        if (pinCount.isStrike()) {
            return new Spare(PinCount.of(PIN_COUNT), pinCount);
        }
        return new Miss(PinCount.of(PIN_COUNT), pinCount);
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
