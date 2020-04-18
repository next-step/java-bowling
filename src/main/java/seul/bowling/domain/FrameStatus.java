package seul.bowling.domain;

import lombok.Getter;

public enum FrameStatus {
    INIT(0, 0),
    HOLD(0, 0),
    STRIKE(2, 2),
    SPARE(1, 1),
    MISS(0, 0);

    @Getter
    private int bonusPlay;
    @Getter
    private int bonusScoreCount;

    FrameStatus(int bonusPlay, int bonusScoreCount) {
        this.bonusPlay = bonusPlay;
        this.bonusScoreCount = bonusScoreCount;
    }

    public FrameStatus judgmentStatus(boolean allClear) {
        if (endStatus())
            return this;

        if (isFirst() && allClear) {
            return STRIKE;
        }

        if (!isFirst() && allClear) {
            return SPARE;
        }

        if (!isFirst()) {
            return MISS;
        }

        return HOLD;
    }

    public boolean endStatus() {
        return this != HOLD && this != INIT;
    }

    public boolean isStrike() {
        return this == STRIKE;
    }

    private boolean isFirst() {
        return this == INIT;
    }
}
