package bowling;

import bowling.rollresult.RollResultType;

public class RollResult {
    private final RollResultType type;
    private final int total;

    public RollResult(RollResultType type, int total) {
        this.type = type;
        this.total = total;
    }

    public static RollResult of() {
        return null;
    }
}
