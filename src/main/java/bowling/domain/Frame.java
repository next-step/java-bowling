package bowling.domain;

public abstract class Frame {
    abstract Frame execute(final BowlingPins bowlingPins);

    public abstract int getFrameNumber();
}
