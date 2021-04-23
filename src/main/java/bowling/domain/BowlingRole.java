package bowling.domain;

import java.util.Arrays;

public enum BowlingRole {
    STRIKE(10, true),
    SPARE(10, false),
    MISS(0, false),
    EMPTY(-1, false);

    private final int total;
    private final boolean isStrike;

    BowlingRole(int total, boolean isStrike) {
        this.total = total;
        this.isStrike = isStrike;
    }

    public int getTotal() {
        return total;
    }

    public boolean isStrike() {
        return isStrike;
    }

    public static BowlingRole valueOf(Score score) {
        return Arrays.stream(values())
                .filter(role -> role.test(score))
                .findAny()
                .orElse(MISS);
    }
    
    public boolean test(Score score) {
        return score.total() == getTotal() && score.isStrike() == isStrike();
    }
}
