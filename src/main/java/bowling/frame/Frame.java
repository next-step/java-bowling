package bowling.frame;

public abstract class Frame {

    private static int FIRST_FRAME_NUMBER = 1;
    private static int FINAL_FRAME_NUMBER = 10;

    private int frameNumber;

    public abstract Frame nextFrame();

    public int getFrameNumber() {
        return frameNumber;
    }

}
