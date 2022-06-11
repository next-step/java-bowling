package bowling.domain;

public class Frames {
    private static final int NUMBERS_OF_NORMAL_FRAME = 9;

    private FrameLinkedList frames;

    public Frames() {
        initial();
    }

    private void initial() {
        frames =  new FrameLinkedList();
        for (int frameIndex = 0; frameIndex < NUMBERS_OF_NORMAL_FRAME - 1; frameIndex++) {
            frames.add(new NormalFrame());
        }
        frames.add(new FinalFrame());
    }


    public FrameLinkedList getFrames() {
        return frames;
    }
}
