package bowling.domain.frame;

import bowling.domain.State.StateType;
import bowling.dto.NormalFrameResult;

import java.util.Arrays;
import java.util.List;

public class NormalFrame implements Frame {

    private final PinCounts pinCounts;

    private final FrameNumber frameNumber;

    private Frame nextFrame;

    private NormalFrame(FrameNumber frameNumber, PinCounts pinCounts, Frame nextFrame) {
        this.frameNumber = frameNumber;
        this.pinCounts = pinCounts;
        this.nextFrame = nextFrame;
    }

    public static NormalFrame first() {
        return new NormalFrame(FrameNumber.first(), new PinCounts(), null);
    }

    public static NormalFrame of(FrameNumber frameNumber, List<PinCount> pinCounts, Frame nextFrame) {
        return new NormalFrame(frameNumber, new PinCounts(pinCounts), nextFrame);
    }

    public NormalFrame next() {
        NormalFrame next = new NormalFrame(frameNumber.next(), new PinCounts(), null);
        this.nextFrame = next;
        return next;
    }


    public FinalFrame last() {
        FinalFrame next = FinalFrame.of(frameNumber.next(), Arrays.asList(NormalFrame.first()));
        this.nextFrame = next;
        return next;
    }

    public void addPinCount(int pinCount) {
        addPinCount(new PinCount(pinCount));
    }

    public void addPinCount(PinCount pinCount) {
        pinCounts.add(pinCount);
    }

    public boolean isDone() {
        return pinCounts.isDone();
    }

    @Override
    public Frame nextFrame() {
        return nextFrame;
    }

    public NormalFrameResult result() {
        return new NormalFrameResult(frameNumber, pinCounts);
    }

    public FrameNumber number() {
        return frameNumber;
    }

    @Override
    public boolean isLast() {
        return nextFrame == null;
    }

    public boolean isMatchCurrentState(StateType stateType) {
        return pinCounts.isMatchCurrentState(stateType);
    }

    public int hitCount() {
        return pinCounts.hitCount();
    }

}
