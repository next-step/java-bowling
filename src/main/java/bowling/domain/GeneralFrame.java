package bowling.domain;

import java.util.List;

public class GeneralFrame implements Frame {

    private final int frameNo;
    private Frame prevFrame;
    private PinMarks pinMarks;

    GeneralFrame(int frameNo, Frame prevFrame) {
        this.frameNo = frameNo;
        this.prevFrame = prevFrame;
        this.pinMarks = new GeneralFramePinMarks();
    }

    @Override
    public int getFrameNo() {
        return frameNo;
    }

    @Override
    public void mark(int countOfFallDown) {
        pinMarks.mark(PinMark.pin(countOfFallDown));
    }

    @Override
    public boolean isEnd() {
        return pinMarks.isAllMarked();
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
        return pinMarks.toImmutableList();
    }

    @Override
    public int getScore() {
        return 0;
    }
}
