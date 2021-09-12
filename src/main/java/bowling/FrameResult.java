package bowling;

import java.util.Arrays;
import java.util.List;

public enum FrameResult {
    STRIKE(2), SPARE(1), MISS(0), NONE(-1);

    private final int bonusDepth;

    FrameResult(int bonusDepth) {
        this.bonusDepth = bonusDepth;
    }

    private static final List<FrameResult> bonusResults = Arrays.asList(STRIKE, SPARE);

    public boolean isBonusResult() {
        return bonusResults.contains(this);
    }

    public int getBonusDepth() {
        return bonusDepth;
    }
}
