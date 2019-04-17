package com.hyoj.bowling.domain.status;

import com.hyoj.bowling.domain.Shot;

import java.util.List;

public interface ResultStatus {
    static ResultStatus getResultStatusInstance(List<Shot> shots) {
        final int shotTimes = shots.size();
        if (shotTimes == 0) {
            return None.getInstance();
        }

        final Shot finalShot = shots.get(shotTimes - 1);

        if (shotTimes == 1 && finalShot.isAllDown()) {
            return Strike.getInstance();
        }

        if (shotTimes == 2 && finalShot.isAllDown()) {
            return Spare.getInstance();
        }

        if (finalShot.isAllStanding()) {
            return Gutter.getInstance();
        }

        return Miss.getInstance();
    }

    boolean canOneMoreShotAtFinal();
}
