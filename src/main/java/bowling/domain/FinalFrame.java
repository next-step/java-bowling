package bowling.domain;

public class FinalFrame implements Frame {

    private final int frameNo;
    private PinMarks pinMarks;

    FinalFrame(int frameNo) {
        this.frameNo = frameNo;
        this.pinMarks = new FinalFramePinMarks();
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
    public Status getStatus() {
        if (pinMarks.isStrike()) return Status.Strike;
        if (pinMarks.getCountOfFallDownPins() == PinMark.MAX_PINS) return Status.Spare;
        if (pinMarks.getCountOfFallDownPins() == 0) return Status.Gutter;
        return Status.Miss;
    }

    @Override
    public boolean isEnd() {
        return pinMarks.isAllMarked();
    }

    @Override
    public Frame nextFrame() {
        return null;
    }

    @Override
    public int getScore() {
        return 0;
    }

    @Override
    public FrameInfo toFrameInfo() {
        return FrameInfo.of(getFrameNo(), pinMarks.toSigns(), getScore());
    }
}
