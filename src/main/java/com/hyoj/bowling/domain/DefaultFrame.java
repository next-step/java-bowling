package com.hyoj.bowling.domain;

import static java.util.stream.Collectors.joining;

import com.hyoj.bowling.domain.status.None;
import com.hyoj.bowling.domain.status.ResultStatus;
import com.hyoj.bowling.domain.status.Spare;
import com.hyoj.bowling.domain.status.Strike;
import java.util.ArrayList;
import java.util.List;

public class DefaultFrame implements Frame {
    private final List<Pins> thrownPins = new ArrayList<>();
    private final int index;

    private DefaultFrame(int index) {
        this.index = index;
    }

    public static Frame createFirstFrame() {
        return new DefaultFrame(1);
    }

    public Frame createNextFrame() {
        final int indexPlusOne = this.index + 1;
        if (indexPlusOne == GameBoard.MAX_FRAMES_COUNT) {
            return createFinalFrame();
        }

        return new DefaultFrame(indexPlusOne);
    }

    public Frame createFinalFrame() {
        return new FinalFrame(new DefaultFrame(GameBoard.MAX_FRAMES_COUNT));
    }

    @Override
    public DefaultFrame throwBowlingBall(Pins pins) {
        final int thrownCount = thrownPins.size();

        if (thrownCount > MAX_THROW_COUNT) {
            throw new IllegalArgumentException("유효하지 않은 투구 횟수");
        }

        if (thrownCount == 0) {
            thrownPins.add(pins);
            return this;
        }

        thrownPins.add(thrownPins.get(thrownCount - 1).next(pins));
        return this;
    }

    @Override
    public ResultStatus getResultStatus() {
        final int shotTimes = thrownPins.size();
        if (shotTimes == 0) {
            return None.getInstance();
        }

        return ResultStatus.getResultStatusInstance(thrownPins);
    }

    @Override
    public boolean isDone() {
        final int shotsCount = thrownPins.size();

        if (shotsCount == 0) {
            return false;
        }

        return shotsCount == MAX_THROW_COUNT || Pins.isAllDown(thrownPins);
    }

    @Override
    public String toString() {
        final ResultStatus resultStatus = ResultStatus.getResultStatusInstance(thrownPins);

        if (resultStatus == Strike.getInstance()) {
            return resultStatus.toString();
        }

        if (resultStatus == Spare.getInstance()) {
            return thrownPins.get(0) + "|" + Spare.MARK;
        }

        return thrownPins.stream()
            .map(Pins::toString)
            .collect(joining("|"));
    }
}