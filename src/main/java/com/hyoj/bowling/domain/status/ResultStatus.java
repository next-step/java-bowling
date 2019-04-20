package com.hyoj.bowling.domain.status;

import com.hyoj.bowling.domain.PinsByFrame;

public interface ResultStatus {
    static ResultStatus getResultStatusInstance(final PinsByFrame pinsByFrame) {
        final int throwCount = pinsByFrame.size();
        if (throwCount == 0) {
            return None.getInstance();
        }

        if (throwCount == 1 && pinsByFrame.isAllDown()) {
            return Strike.getInstance();
        }

        if (throwCount == 2 && pinsByFrame.isAllDown()) {
            return Spare.getInstance();
        }

        if (pinsByFrame.isAllStanding()) {
            return Gutter.getInstance();
        }

        return Miss.getInstance();
    }

    boolean canOneMoreShotAtFinal();
}
