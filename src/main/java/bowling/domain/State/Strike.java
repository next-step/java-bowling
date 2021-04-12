package bowling.domain.State;

import bowling.domain.Score;
import bowling.domain.frame.PinCount;

public class Strike implements State {

    private final static String SYMBOL = "X";

    private final static int PIN_COUNT = 10;

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
        return Score.of(PIN_COUNT,BONUS_COUNT);
    }

    @Override
    public Score calculateScore(Score score) {
        if(!score.isDoneCalculating()){
            return score.calculatedScore(PIN_COUNT);
        }
        return score;
    }

}
