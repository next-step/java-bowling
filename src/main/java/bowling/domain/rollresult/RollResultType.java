package bowling.domain.rollresult;

import bowling.domain.Score;

public abstract class RollResultType {
    static int DEFAULT_MAX_SCORE = 10;
    static int DEFAULT_MIN_SCORE = 0;
    public abstract boolean isStrike();
    public abstract boolean isSpare();
    public abstract boolean hasNext();
    public abstract Score eval();
    public abstract RollResultType next(int nextHit);
}
