package bowling.domain;

import java.util.List;

public class GeneralFrame implements Frame {

    private final int frameNo;
    private Frame prevFrame;
    private PinMarks marks;

    GeneralFrame(int frameNo, Frame prevFrame) {
        this.frameNo = frameNo;
        this.prevFrame = prevFrame;
        this.marks = new GeneralFramePinMarks();
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
        return false;
    }

    @Override
    public Frame nextFrame() {
        if (frameNo == 9)
            return Frame.last(frameNo + 1, this);
        return Frame.general(frameNo + 1, this);
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
