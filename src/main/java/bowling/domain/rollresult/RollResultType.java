package bowling.domain.rollresult;

import bowling.domain.Score;

public abstract class RollResultType {

    public abstract boolean isStrike();
    public abstract boolean isSpare();
    public abstract boolean hasNext();
    public abstract boolean canAccumulate();
    public abstract Score eval();
    public abstract RollResultType next(int nextHit);
}
