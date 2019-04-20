package com.hyoj.bowling.domain;

import static java.util.stream.Collectors.joining;

import com.hyoj.bowling.console.OutputConsole;
import com.hyoj.bowling.domain.status.ResultStatus;
import com.hyoj.bowling.domain.status.Spare;
import com.hyoj.bowling.domain.status.Strike;
import java.util.ArrayList;
import java.util.List;

public class FinalFrame implements Frame {
    private final Frame frame;
    private final PinsByFrame pinsByFrame = new PinsByFrame(true);
    private List<Frame> optionalFrames = new ArrayList<>();

    public FinalFrame(DefaultFrame frame) {
        this.frame = frame;
    }

    @Override
    public FinalFrame throwBowlingBall(final Pins pins) {
        if (!frame.isDone()) {
            frame.throwBowlingBall(pins);
            return this;
        }

        final int optionalThrowCount = optionalFrames.size();

        if (optionalThrowCount > MAX_THROW_COUNT || isDone()) {
            throw new IllegalArgumentException("유효하지 않은 투구 횟수");
        }

        optionalFrames.add(DefaultFrame.createFirstFrame().throwBowlingBall(pins));
        return this;
    }

    @Override
    public ResultStatus getResultStatus() {
        final int throwCount = pinsByFrame.size();
        if (throwCount == 0) {
            return frame.getResultStatus();
        }

        return pinsByFrame.getResultStatusInstance();
    }

    @Override
    public boolean isDone() {
        final ResultStatus defaultResultStatus = frame.getResultStatus();
        final boolean canOneMoreShot = defaultResultStatus.canOneMoreShotAtFinal();
        final int optionalFrameCount = optionalFrames.size();

        if (!frame.isDone()) {
            return false;
        }

        if (!canOneMoreShot) {
            return true;
        }

        if (optionalFrameCount == 0) {
            return false;
        }

        if (optionalFrameCount == 1 && defaultResultStatus == Spare.getInstance()) {
            return true;
        }

        if (optionalFrameCount == 1 &&
            optionalFrames.get(0).getResultStatus() != Strike.getInstance()) {
            return true;
        }

        return optionalFrameCount == MAX_THROW_COUNT;
    }

    @Override
    public String toString() {
        final int optionalThrowCount = optionalFrames.size();

        if (!frame.isDone() || optionalThrowCount == 0) {
            return frame.toString();
        }

        return frame + OutputConsole.BAR + optionalFrames.stream()
            .map(Object::toString)
            .collect(joining("|"));
    }
}
