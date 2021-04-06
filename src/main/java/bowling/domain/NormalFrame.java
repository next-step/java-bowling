package bowling.domain;

import bowling.dto.NormalFrameResult;

import java.util.ArrayList;
import java.util.List;

public class NormalFrame {

    private static final int MAX_TRY_COUNT = 2;

    private static final int MAX_TOTAL_PIN_COUNTS = 10;

    private final List<PinCount> pinCounts = new ArrayList<>();

    private final FrameNumber frameNumber;

    private NormalFrame(FrameNumber frameNumber, List<PinCount> pinCounts) {
        this.frameNumber = frameNumber;
        initializePinCounts(pinCounts);
    }

    private void initializePinCounts(List<PinCount> pinCounts) {
        try {
            pinCounts.forEach(this::addPinCount);
        } catch (IllegalArgumentException | IllegalStateException exception) {
            throw new IllegalArgumentException("유효하지 않는 투구 값들 입니다.");
        }
    }

    private void validateToAddPinCount(PinCount pinCount) {
        if (totalPinCounts(this.pinCounts) + pinCount.count() > MAX_TOTAL_PIN_COUNTS) {
            throw new IllegalArgumentException("투구 결과 핀수가 너무 많습니다.");
        }
    }

    private FrameScoreResult scoreResult() {
        return FrameScoreResult.of(totalPinCounts(this.pinCounts), pinCounts.size());
    }

    public NormalFrame(int frameNumber) {
        this(new FrameNumber(frameNumber), new ArrayList<>());
    }

    public NormalFrame(FrameNumber frameNumber) {
        this(frameNumber, new ArrayList<>());
    }

    public static NormalFrame first() {
        return new NormalFrame(FrameNumber.first());
    }

    public static NormalFrame of(FrameNumber frameNumber, List<PinCount> pinCounts) {
        return new NormalFrame(frameNumber, pinCounts);
    }

    public NormalFrame next() {
        return new NormalFrame(frameNumber.next());
    }

    public void addPinCount(int pinCount) {
        addPinCount(new PinCount(pinCount));
    }

    public void addPinCount(PinCount pinCount) {
        if (isDone()) {
            throw new IllegalStateException("이미 끝난 프레임 입니다.");
        }
        validateToAddPinCount(pinCount);
        pinCounts.add(pinCount);
    }

    private int totalPinCounts(List<PinCount> pinCounts) {
        return pinCounts.stream()
                .map(PinCount::count)
                .reduce(0, Integer::sum);
    }

    public boolean isDone() {
        return pinCounts.size() >= MAX_TRY_COUNT || totalPinCounts(this.pinCounts) >= MAX_TOTAL_PIN_COUNTS;
    }

    public NormalFrameResult result() {
        return new NormalFrameResult(frameNumber,null);
    }

    public FrameNumber number() {
        return frameNumber;
    }
}
