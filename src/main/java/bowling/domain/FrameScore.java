package bowling.domain;

import bowling.util.BowlingUtils;

import java.util.ArrayList;
import java.util.List;

public class FrameScore {
    private static final int BONUS_STANDARD_PINS = 10;
    private static final int BONUS_SHOT_COUNT = 3;
    private static final int DEFAULT_SHOT_COUNT = 2;

    private List<Shot> shots = new ArrayList<>();

    public boolean isFinished() {
        if (BowlingUtils.sum(shots) < BONUS_STANDARD_PINS) {
            return shots.size() == DEFAULT_SHOT_COUNT;
        }
        return shots.size() == BONUS_SHOT_COUNT;
    }

    public void addPoint(Shot shot) {
        if (isFinished()) {
            throw new IllegalStateException("Already Finished Scoring frame");
        }

        shots.add(shot);
    }

    public int calculateFrameScore() {
        return BowlingUtils.sum(shots);
    }
}
