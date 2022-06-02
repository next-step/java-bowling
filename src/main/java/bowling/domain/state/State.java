package bowling.domain.state;

import bowling.domain.score.Score;
import bowling.domain.state.exception.BonusBowlingException;

public abstract class State {

    private static final int ZERO = 0;

    public abstract State bowling(int pins);

    public abstract String symbol();

    public abstract int totalScore();

    public abstract Score calculateScore(Score before);

    public Score createScore() {
        return new Score(ZERO, this.totalScore());
    }

    public State bonusBowling(int pins) {
        throw new BonusBowlingException();
    }

    public <T extends State> boolean is(Class<T> clazz) {
        return this.getClass().isAssignableFrom(clazz);
    }
}
