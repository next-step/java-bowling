package com.hyoj.bowling.domain;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;

public class DefaultFrame implements Frame {
    public static final int MAX_SHOTS_COUNT = 2;

    private final List<Shot> shots = new ArrayList<>();

    @Override
    public DefaultFrame add(int knockDownPinsCount) {
        final int shotTimes = shots.size();

        if (shotTimes > MAX_SHOTS_COUNT) {
            throw new IllegalArgumentException("유효하지 않은 투구 횟수");
        }

        if (shotTimes == 0) {
            shots.add(new Shot(knockDownPinsCount));
            return this;
        }

        shots.add(new Shot(knockDownPinsCount, shots.get(shotTimes - 1)));
        return this;
    }

    public MarkType getFinalMarkType() {
        final int shotTimes = shots.size();
        return MarkType.makeMarkType(shotTimes, shots.get(shotTimes - 1));
    }

    @Override
    public boolean isDone() {
        final int shotsCount = shots.size();
        if (shotsCount == MAX_SHOTS_COUNT || shots.get(shotsCount - 1).isAllDown()) {
            return true;
        }

        return false;
    }

    @Override
    public boolean canOneMoreShot() {
        return shots.get(shots.size() - 1).isAllDown();
    }

    @Override
    public String toString() {
        final MarkType finalMarkType = getFinalMarkType();

        if (finalMarkType.equals(MarkType.STRIKE)) {
            return finalMarkType.toString();
        }

        return shots.stream()
            .map(Shot::toString)
            .collect(joining("|"));
    }
}