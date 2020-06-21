package bowling.domain.pitch;

import bowling.domain.bowling.BowlingPinsGroup;

public class Pitch {
    private static final int MINIMUM_HIT_COUNTS = 0;
    private static final int MAXIMUM_HIT_COUNTS = 10;

    private final int hitCounts;

    public Pitch(int hitCounts) {
        this.hitCounts = hitCounts;
    }

    public void throwBall(BowlingPinsGroup bowlingPinsGroup) {
        bowlingPinsGroup.hitByBall(hitCounts);
    }

    public boolean isStrike() {
        return hitCounts == MAXIMUM_HIT_COUNTS;
    }

    public boolean isGutter() {
        return hitCounts == MINIMUM_HIT_COUNTS;
    }

    public int getHitCounts() {
        return hitCounts;
    }
}
