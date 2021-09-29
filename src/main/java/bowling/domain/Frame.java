package bowling.domain;

import bowling.exception.CannotCreateException;

public abstract class Frame {
    public abstract int score();
    public abstract FrameStatus status();
    public abstract void firstShot(PinScore pinScore);
    public abstract void secondShot(PinScore pinScore) throws CannotCreateException;
}
