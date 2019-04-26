package com.hyoj.bowling.domain;

import com.hyoj.bowling.domain.status.None;
import com.hyoj.bowling.domain.status.ResultStatus;
import com.hyoj.bowling.domain.status.Strike;

public class DefaultFrame implements Frame {
    private final int index;
    private final PinsByFrame pinsByFrame = new PinsByFrame(false);

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
        final int throwCount = pinsByFrame.size();

        if (throwCount > MAX_THROW_COUNT) {
            throw new IllegalArgumentException("유효하지 않은 투구 횟수");
        }

        pinsByFrame.add(pins);
        return this;
    }

    @Override
    public ResultStatus getResultStatus() {
        final int throwCount = pinsByFrame.size();
        if (throwCount == 0) {
            return None.getInstance();
        }

        return pinsByFrame.getResultStatusInstance();
    }

    @Override
    public boolean isDone() {
        final int shotsCount = pinsByFrame.size();

        if (shotsCount == 0) {
            return false;
        }

        return shotsCount == MAX_THROW_COUNT || pinsByFrame.isAllDown();
    }

    @Override
    public String toString() {
        final ResultStatus resultStatus = pinsByFrame.getResultStatusInstance();

        if (resultStatus == Strike.getInstance()) {
            return resultStatus.toString();
        }

        return pinsByFrame.toString();
    }
}