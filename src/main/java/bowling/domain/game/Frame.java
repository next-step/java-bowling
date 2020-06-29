package bowling.domain.game;

import bowling.domain.pin.FramePins;

import java.util.Objects;

public abstract class Frame {
    private final FrameNumber frameNumber;
    private final FramePins framePins;

    protected Frame(final FrameNumber frameNumber, final FramePins framePins) {
        Objects.requireNonNull(frameNumber);
        Objects.requireNonNull(framePins);
        this.frameNumber = frameNumber;
        this.framePins = framePins;
    }

    public abstract Frame next();

    public abstract boolean isFinalFrame();
}
