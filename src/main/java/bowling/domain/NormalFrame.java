package bowling.domain;

public class NormalFrame extends Frame {
    private static final int FINAL_NORMAL_FRAME_NUMBER = 9;

    private final int frameNumber;
    private Frame next;

    public NormalFrame(int frameNumber){
        this.frameNumber = frameNumber;
        fitch = new NormalFitch();
    }

    @Override
    public int getFrameNumber() {
        return frameNumber;
    }

    @Override
    public Frame next() {
        if(isFinish()){
            return getFrame();
        }
        return this;
    }

    private Frame getFrame() {
        if(frameNumber == FINAL_NORMAL_FRAME_NUMBER){
            next = new FinalFrame();
            return next;
        }
        next = new NormalFrame(frameNumber + 1);
        return next;
    }
}
