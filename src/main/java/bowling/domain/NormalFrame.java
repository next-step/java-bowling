package bowling.domain;

import java.util.List;

public class NormalFrame implements PlayableFrame {

    private static final int MAX_TRY_COUNT = 2;

    private final Frame frame;

    public NormalFrame(int number) {
        this.frame = new Frame(number);
    }

    public static NormalFrame first() {
        return new NormalFrame(1);
    }

    public FinalFrame last() {
        return new FinalFrame(number + 1);
    }

    public NormalFrame next() {
        return new NormalFrame(number + 1);
    }

    public FrameNumber number() {
        return frame.number();
    }

    @Override
    public void addPintCount(int pinCount) {
        if (isDone()) {
            throw new IllegalStateException("이미 끝난 프레임 입니다.");
        }
        frame.addPintCount(pinCount);
    }

    @Override
    public List<PinCount> pinCounts() {
        return frame.pinCounts();
    }

    @Override
    public boolean isDone() {
        int totalPinCounts = frame.totalPinCounts();
        if (frame.hasCle() || frame.isReachedMaxTryCount(MAX_TRY_COUNT)) {
            return true;
        }
        return false;
    }
}
