package bowling.domain;

abstract public class BaseFrame implements Frame {

    protected final int frameNo;
    protected final PinMarker pinMarker;
    protected Frame next;

    BaseFrame(int frameNo, PinMarker pinMarker){
        this.frameNo = frameNo;
        this.pinMarker = pinMarker;
    }

    @Override
    public int getFrameNo() {
        return frameNo;
    }

    @Override
    public void mark(int countOfFallDownPins) {
        pinMarker.mark(PinMark.pin(countOfFallDownPins));
    }

    @Override
    public boolean isEnd() {
        return pinMarker.isCompleted();
    }

    @Override
    public Frame next() {
        return next;
    }

    @Override
    public FrameInfo toFrameInfo() {
        return FrameInfo.of(
                getFrameNo(),
                pinMarker.toSymbols(),
                getScore());
    }

}
