package bowling.domain;

public interface Frame {
    Frame throwBowl(String pinCount);

    boolean isFinished();

    int index();

    FrameBowls bowls();

    Frame next();
}
