package com.hyoj.bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Frame {
    private final List<Shot> shots = new ArrayList<>();

    public boolean add(int knockDownPinsCount) {
        final int shotTimes = shots.size();

        if (shotTimes > 2) {
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
}
