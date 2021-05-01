package bowling.domain;

public interface Frame {
    Frame roll(Pinfall pinfall);

    Frame roll(Pinfall pinfall, FrameFatory frameFatory);

    FrameResult result();

    boolean isDone();

    FrameNumber frameNumber();
}
