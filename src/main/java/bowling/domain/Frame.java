package bowling.domain;

public interface Frame {
    Frame throwBowl(String pinCount);

    boolean isFinished();

    int index();

    PinCounts pinCounts();

    Frame next();
}
