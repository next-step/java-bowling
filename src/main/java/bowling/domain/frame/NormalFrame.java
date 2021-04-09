package bowling.domain.frame;

import bowling.domain.State.StateType;
import bowling.dto.NormalFrameResult;

import java.util.Arrays;
import java.util.List;

public class NormalFrame implements Frame {

//    private static final int MAX_TRY_COUNT = 2;
//
//    private static final int MAX_TOTAL_PIN_COUNTS = 10;
//
//    private final List<PinCount> pinCounts = new ArrayList<>();

    private final PinCounts pinCounts;

    private final FrameNumber frameNumber;

    private Frame nextFrame;

    private NormalFrame(FrameNumber frameNumber, PinCounts pinCounts, Frame nextFrame) {
        this.frameNumber = frameNumber;
        this.pinCounts = pinCounts;
        this.nextFrame = nextFrame;
    }

//    private void initializePinCounts(List<PinCount> pinCounts) {
//        try {
//            pinCounts.forEach(this::addPinCount);
//        } catch (IllegalArgumentException | IllegalStateException exception) {
//            throw new IllegalArgumentException("유효하지 않는 투구 값들 입니다.");
//        }
//
//    }

//    private void validateToAddPinCount(PinCount pinCount) {
//        if (totalPinCounts(this.pinCounts) + pinCount.count() > MAX_TOTAL_PIN_COUNTS) {
//            throw new IllegalArgumentException("투구 결과 핀수가 너무 많습니다.");
//        }
//    }

    public static NormalFrame first() {
        return new NormalFrame(FrameNumber.first(), new PinCounts(), null);
    }

    public static NormalFrame of(FrameNumber frameNumber, List<PinCount> pinCounts, Frame nextFrame) {
        return new NormalFrame(frameNumber, new PinCounts(pinCounts),nextFrame);
    }

    public NormalFrame next() {
        NormalFrame next = new NormalFrame(frameNumber.next(), new PinCounts(), null);
        this.nextFrame = next;
        return next;
    }


    public FinalFrame last() {
        FinalFrame next = FinalFrame.of(frameNumber.next(),Arrays.asList(NormalFrame.first()));
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
//    public StateType currentState() {
//       return pinCounts.currentState();
//    }
//
//    public List<Integer> pinCountsAsInt() {
//        return pinCounts.pinCountsAsInt();
//    }
}
