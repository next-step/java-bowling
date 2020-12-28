package bowling.domain;

import java.util.List;

public class LastFrame implements Frame {

    private final int frameNo;
    private final Frame prevFrame;
    private PinMarks marks;

    LastFrame(int frameNo, Frame prevFrame) {
        this.frameNo = frameNo;
        this.prevFrame = prevFrame;
        this.marks = new LastFramePinMarks();
    }

    @Override
    public int getFrameNo() {
        return frameNo;
    }

    @Override
    public void inputPins(int countOfFallDown) {
        marks.mark(PinMark.pin(countOfFallDown));
    }

    @Override
    public boolean isEnd() {
        return marks.isAllMarked();
    }

    @Override
    public boolean isLast() {
        return true;
    }

    @Override
    public Frame nextFrame() {
        return null;
    }

    @Override
    public List<PinMark> getPinMarks() {
        return marks.toImmutableList();
    }

    @Override
    public int getScore() {
        return 0;
    }
}
