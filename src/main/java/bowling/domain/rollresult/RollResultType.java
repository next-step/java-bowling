package bowling.domain.rollresult;

public abstract class RollResultType {
    static int DEFAULT_MAX_SCORE = 10;
    static int DEFAULT_MIN_SCORE = 0;
    public abstract boolean isStrike();
    public abstract boolean isSpare();
    public abstract boolean hasNext();
    public abstract int eval();
    public abstract RollResultType next(int nextHit);
}
