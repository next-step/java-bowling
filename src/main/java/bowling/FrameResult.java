package bowling;

import java.util.Arrays;
import java.util.List;

public enum FrameResult {
    NONE, STRIKE, SPARE, MISS;

    private static final List<FrameResult> bonusResults = Arrays.asList(STRIKE, SPARE);

    public boolean isBonusResult() {
        return bonusResults.contains(this);
    }
}
