package bowling.domain.frame;

import bowling.domain.KnockedPins;

public abstract class DefaultFrame implements Frame {
    public static final int thirdScoreKey = 3;
    public static final int firstScoreKey = 1;
    public static final int secondScoreKey = 2;
    protected KnockedPins firstKnockedPins;
    protected KnockedPins secondKnockedPins;

    public KnockedPins getFirstScore() {
        return firstKnockedPins;
    }
    public KnockedPins getSecondScore() {
        return secondKnockedPins;
    }

    public boolean isEmpty() {
        return firstKnockedPins == null;
    }

    public boolean isStrike() {
        return firstKnockedPins.equals(new KnockedPins(10));
    }
}
