package com.hyoj.bowling.domain;

import static java.util.stream.Collectors.joining;

import com.hyoj.bowling.domain.status.ResultStatus;
import com.hyoj.bowling.domain.status.Spare;
import java.util.ArrayList;
import java.util.List;

public class PinsByFrame {
    private final List<Pins> knockDownPins = new ArrayList<>();
    private final boolean isForFinalFrame;

    public PinsByFrame(boolean isForFinalFrame) {
        this.isForFinalFrame = isForFinalFrame;
    }

    public int size() {
        return knockDownPins.size();
    }

    public PinsByFrame add(Pins pins) {
        int throwCount = knockDownPins.size();

        if (throwCount == 0 || isForFinalFrame) {
            knockDownPins.add(pins);
            return this;
        }

        knockDownPins.add(knockDownPins.get(throwCount - 1).next(pins));

        return this;
    }

    public ResultStatus getResultStatusInstance() {
        return ResultStatus.getResultStatusInstance(this);
    }

    public boolean isAllDown() {
        return Pins.isAllDown(knockDownPins);
    }

    public boolean isAllStanding() {
        return Pins.isAllStanding(knockDownPins);
    }

    @Override
    public String toString() {
        final ResultStatus resultStatus = getResultStatusInstance();

        if (resultStatus == Spare.getInstance()) {
            return knockDownPins.get(0) + "|" + Spare.MARK;
        }

        return knockDownPins.stream()
            .map(Pins::toString)
            .collect(joining("|"));
    }
}