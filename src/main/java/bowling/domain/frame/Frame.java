package bowling.domain.frame;

import bowling.domain.score.Score;

public abstract class Frame {

    protected static final int firstFrame = 1;
    protected static final int lastFrame = 10;

    private final int frameNumber;

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
        return new NormalFrame(firstFrame);
    }

    public Frame createNextFrame() {
        int nextFrameNumber = frameNumber + 1;

        if (nextFrameNumber == lastFrame) {
            return new LastFrame();
        }

        return new NormalFrame(nextFrameNumber);
    }

    protected abstract Score addBonus(Score originalScore);

    public abstract int calculateScore();
}
