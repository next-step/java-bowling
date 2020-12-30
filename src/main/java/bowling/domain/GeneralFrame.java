package bowling.domain;

public class GeneralFrame implements Frame {

    private final int frameNo;
    private Frame next;
    private PinMarks pinMarks;

    GeneralFrame(int frameNo) {
        this.frameNo = frameNo;
        this.pinMarks = new GeneralFramePinMarks();
    }

    @Override
    public int getFrameNo() {
        return frameNo;
    }

    @Override
    public void mark(int countOfFallDown) {
        pinMarks.mark(countOfFallDown);
    }

    @Override
    public Status getStatus() {
        if (pinMarks.isStrike()) return Status.Strike;
        if (pinMarks.getCountOfFallDownPins() == 0) return Status.Gutter;
        if (pinMarks.getCountOfFallDownPins() == PinMark.MAX_PINS) return Status.Spare;
        return Status.Miss;
    }

    @Override
    public boolean isEnd() {
        return pinMarks.isAllMarked();
    }

    @Override
    public Frame nextFrame() {
        if (isNextFrameLast()) {
            next = Frame.last(frameNo + 1);
            return next;
        }

        next = Frame.general(frameNo + 1);
        return next;
    }

    private boolean isNextFrameLast() {
        return frameNo == 9;
    }

    @Override
    public int getScore() {
        return 0;
    }

    @Override
    public FrameData toFrameData() {
        return FrameData.of(frameNo, pinMarks.toSigns(), getScore());
    }
}
