package com.hyoj.bowling.domain.status;

import com.hyoj.bowling.domain.Pins;
import java.util.List;

public interface ResultStatus {
    static ResultStatus getResultStatusInstance(List<Pins> pins) {
        final int thrownCount = pins.size();
        if (thrownCount == 0) {
            return None.getInstance();
        }

        if (thrownCount == 1 && Pins.isAllDown(pins)) {
            return Strike.getInstance();
        }

        if (thrownCount == 2 && Pins.isAllDown(pins)) {
            return Spare.getInstance();
        }

        if (Pins.isAllStanding(pins)) {
            return Gutter.getInstance();
        }

        return Miss.getInstance();
    }

    boolean canOneMoreShotAtFinal();
}
