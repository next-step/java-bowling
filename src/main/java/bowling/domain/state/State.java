package bowling.domain.state;

import bowling.domain.score.Score;
import bowling.domain.state.exception.BonusBowlingException;

public abstract class State {

    public abstract State bowling(int pins);

    public abstract String symbol();

    public abstract Score score();

    public <T extends State> boolean is(Class<T> clazz) {
        return this.getClass().isAssignableFrom(clazz);
    }

    public State bonusBowling(int pins) {
        throw new BonusBowlingException();
    }
}
