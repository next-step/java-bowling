package com.hyoj.bowling.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.joining;

public class Frame {
    public static final int MAX_SHOTS_COUNT = 2;

    private final List<Shot> shots = new ArrayList<>();

    public boolean add(int knockDownPinsCount) {
        final int shotTimes = shots.size();

        if (shotTimes > MAX_SHOTS_COUNT) {
            throw new IllegalArgumentException("유효하지 않은 투구 횟수");
        }

        if (shotTimes == 0) {
            return shots.add(new Shot(knockDownPinsCount));
        }

        return shots.add(new Shot(knockDownPinsCount, shots.get(shotTimes - 1)));
    }

    public MarkType getFinalMarkType() {
        final int shotTimes = shots.size() - 1;
        return MarkType.makeMarkType(shotTimes, shots.get(shotTimes));
    }

    public boolean isDone() {
        final int shotsCount = shots.size();
        if (shotsCount == MAX_SHOTS_COUNT || shots.get(shotsCount - 1).isAllDown()) {
            return true;
        }

        return false;
    }

    public boolean canOneMoreShot() {
        if (isDone() && (Arrays.asList(MarkType.SPARE, MarkType.STRIKE).contains(getFinalMarkType()))) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        final MarkType finalMarkType = getFinalMarkType();

        if (isDone() && finalMarkType.equals(MarkType.MISS)) {
            return shots.stream()
                    .map(Shot::toString)
                    .collect(joining(" | "));
        }

        return finalMarkType.toString();
    }
}