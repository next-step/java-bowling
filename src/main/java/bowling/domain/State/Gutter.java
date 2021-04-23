package bowling.domain.State;

import bowling.domain.PinCount.PinCount;
import bowling.domain.PinCount.PinCounts;
import bowling.domain.score.Score;
import bowling.domain.score.UnDefinedScore;

import java.util.Arrays;

public class Gutter implements State {

    public static final String SYMBOL = "-";

    @Override
    public State newState(PinCount pinCount) {
        PinCounts pinCounts = new PinCounts(Arrays.asList(PinCount.GUTTER, pinCount));
        if (pinCounts.isSpare()) {
            return new Spare(pinCounts);
        }
        return new Miss(pinCounts);
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
