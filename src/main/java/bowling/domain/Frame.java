package bowling.domain;

public interface Frame {
    Frame roll(Pinfall pinfall);

    FrameResult result();

    boolean isDone();

    public FrameNumber frameNumber();
}
