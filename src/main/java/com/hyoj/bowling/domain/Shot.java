package com.hyoj.bowling.domain;

public class Shot {
    public static final int MAX_COUNT = 10;
    public static final int MIN_COUNT = 0;

    private final int knockDownPinsCount;
    private final int standingPinsCount;

    public Shot(final int knockDownPinsCount) {
        if (knockDownPinsCount < MIN_COUNT || knockDownPinsCount > MAX_COUNT) {
            throw new IllegalArgumentException("유효하지 않은 쓰러진 핀 개수");
        }

        this.knockDownPinsCount = knockDownPinsCount;
        this.standingPinsCount = MAX_COUNT - knockDownPinsCount;
    }

    public Shot(final int knockDownPinsCount, final Shot prevShot) {
        if (knockDownPinsCount + prevShot.knockDownPinsCount > MAX_COUNT) {
            throw new IllegalArgumentException("유효하지 않은 쓰러진 핀 개수");
        }

        this.knockDownPinsCount = knockDownPinsCount;
        this.standingPinsCount = prevShot.standingPinsCount - knockDownPinsCount;
    }

    public boolean isAllDown() {
        if (standingPinsCount == MIN_COUNT) {
            return true;
        }

        return false;
    }

    public boolean isAllStanding() {
        if (standingPinsCount == MAX_COUNT) {
            return true;
        }

        return false;
    }
}
