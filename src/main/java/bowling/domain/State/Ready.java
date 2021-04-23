package bowling.domain.State;

import bowling.domain.PinCount.PinCount;
import bowling.domain.score.Score;
import bowling.domain.score.UnDefinedScore;

public class Ready implements State {

    private final static String SYMBOL = "";

    @Override
    public State newState(PinCount pinCount) {

        if (pinCount.isStrike()) {
            return new Strike();
        }

        if (pinCount.isGutter()) {
            return new Gutter();
        }

        return new Hit(pinCount);
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
