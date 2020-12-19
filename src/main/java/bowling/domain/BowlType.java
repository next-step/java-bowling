package bowling.domain;

import java.util.Arrays;
import java.util.function.Function;

public enum BowlType {

    STRIKE(2),
    SPARED(1),
    MISS(0),
    READY(0),
    NORMAL(0);

    private int bonusCount;

    BowlType(int bonusCount) {
        this.bonusCount = bonusCount;
    }

    public int getBonusCount() {
        return bonusCount;
    }
}
