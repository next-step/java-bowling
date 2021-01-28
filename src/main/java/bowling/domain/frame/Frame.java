package bowling.domain.frame;

public abstract class Frame {

    private static final int firstFrame = 1;
    private static final int lastFrame = 10;

    protected int frameNumber;

    public Frame(int frameNumber) {
        if (frameNumber < firstFrame || frameNumber > lastFrame) {
            throw new IllegalArgumentException("허용되지 않은 프레임 번호입니다.");
        }
        this.frameNumber = frameNumber;
    }

    public abstract Frame record(int downedPin);

    public abstract boolean isEnd();

    public abstract String getDescriptionForm();

    public static Frame createInitialFrame() {
        return new NormalFrame(1);
    }

}
