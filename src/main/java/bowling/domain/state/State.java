package bowling.domain.state;

import bowling.domain.Score;

public abstract class State {
    public abstract boolean isStrike();
    public abstract boolean isSpare();
    public abstract boolean hasNext();
    public abstract boolean canAccumulate();
    public abstract Score eval();
    public abstract State next(int nextHit);
}
