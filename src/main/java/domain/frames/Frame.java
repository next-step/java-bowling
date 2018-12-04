package domain.frames;

public abstract class Frame {
    public abstract Frame bowling(int ball);

    public abstract boolean isSameFrame(Frame nextNormalFrame);

    public abstract boolean isStrike();

    public abstract int getFrameNumber();
}
