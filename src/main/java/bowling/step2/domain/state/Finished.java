package bowling.step2.domain.state;

import bowling.step2.domain.exception.BowlException;

abstract public class Finished implements State {
    private static final String IMPOSSIBLE_BOWL_EXCEPTION = "더이상 투구할 수 없습니다.";
    
    @Override
    public State bowl(final int fallenPins) {
        throw new BowlException(IMPOSSIBLE_BOWL_EXCEPTION);
    }
}
