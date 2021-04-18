package bowling.domain.State;

import bowling.domain.score.Score;
import bowling.domain.score.UnFinishedScore;

public class Strike implements State {

    private final static String SYMBOL = "X";

    private final static int BONUS_COUNT = 2;

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
        return SYMBOL;
    }

    @Override
    public Score score() {
        return new UnFinishedScore(PinCount.STRIKE.count(), BONUS_COUNT);
    }

    @Override
    public Score calculatedScore(Score scoreToCalculate) {
        if (scoreToCalculate.isNecessaryToCalculateMore()) {
            return scoreToCalculate.calculatedScore(PinCount.STRIKE.count());
        }
        return scoreToCalculate;
    }

}
