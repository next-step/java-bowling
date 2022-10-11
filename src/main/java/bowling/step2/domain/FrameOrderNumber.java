package bowling.step2.domain;

public class FrameOrderNumber {
    private static final int COUNT_OF_MAX_FRAME = 10;
    
    private final int frameOrderNumber;
    
    public FrameOrderNumber(final int frameOrderNumber) {
        this.frameOrderNumber = frameOrderNumber;
    }
    
    public int increase() {
        return frameOrderNumber + 1;
    }
    
    public boolean isNextFinalFrame() {
        return frameOrderNumber + 1 == COUNT_OF_MAX_FRAME;
    }
}
