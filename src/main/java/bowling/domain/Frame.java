package bowling.domain;

import bowling.exception.CannotCreateException;

public abstract class Frame {
    public abstract int score();
    public abstract FrameStatus status();
    public abstract void firstShot(PinScore pinScore) throws CannotCreateException;
    public abstract void secondShot(PinScore pinScore) throws CannotCreateException;
    public abstract void linkNextFrame(Frame next);
    public abstract Frame next();



    public static Frame LAST_FRAME = new FinalFrame();
}
