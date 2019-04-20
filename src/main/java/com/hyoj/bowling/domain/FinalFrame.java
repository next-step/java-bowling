package com.hyoj.bowling.domain;

import static java.util.stream.Collectors.joining;

import com.hyoj.bowling.console.OutputConsole;
import com.hyoj.bowling.domain.status.ResultStatus;
import com.hyoj.bowling.domain.status.Spare;
import com.hyoj.bowling.domain.status.Strike;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FinalFrame implements Frame {
    private final Frame frame;
    private final List<Pins> thrownPins = new ArrayList<>();

    public FinalFrame(DefaultFrame frame) {
        this.frame = frame;
    }

    public boolean canOneMoreShot() {
        return getResultStatus().canOneMoreShotAtFinal();
    }

    @Override
    public FinalFrame throwBowlingBall(Pins pins) {
        if (!frame.isDone()) {
            frame.throwBowlingBall(pins);
            return this;
        }

        final int thrownCount = thrownPins.size();

        if (thrownCount > MAX_THROW_COUNT || isDone()) {
            throw new IllegalArgumentException("유효하지 않은 투구 횟수");
        }

        if (thrownCount == 0 || Pins.isAllDown(thrownPins)) {
            thrownPins.add(pins);
            return this;
        }

        thrownPins.add(thrownPins.get(thrownCount - 1).next(pins));
        return this;
    }

    @Override
    public ResultStatus getResultStatus() {
        final int thrownCount = thrownPins.size();
        if (thrownCount == 0) {
            return frame.getResultStatus();
        }

        return ResultStatus.getResultStatusInstance(thrownPins);
    }

    @Override
    public boolean isDone() {
        ResultStatus resultStatus = frame.getResultStatus();
        final boolean canOneMoreShot = resultStatus.canOneMoreShotAtFinal();
        final int thrownCount = thrownPins.size();

        if (!frame.isDone()) {
            return false;
        }

        if (thrownCount == 0 && !canOneMoreShot) {
            return true;
        }

        if (thrownCount == 1 && resultStatus == Spare.getInstance()) {
            return true;
        }

        if (thrownCount == 1 && Pins.isAllDown(thrownPins)) {
            return false;
        }

        return thrownCount == MAX_THROW_COUNT;
    }

    @Override
    public String toString() {
        if (!frame.isDone() || thrownPins.size() == 0) {
            return frame.toString();
        }

        return frame + OutputConsole.BAR + thrownPins.stream()
            .map(p -> Pins.isAllDown(Arrays.asList(p)) ? Strike.getInstance().toString() : p.toString())
            .collect(joining(OutputConsole.BAR));
    }
}
