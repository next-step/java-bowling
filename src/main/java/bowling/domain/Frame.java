package bowling.domain;

public interface Frame {
    Frame throwBowl(String pinCount);

    boolean isFinished();

    int index();

    PinCounts pinCounts();

    Frame next();

    Score score();

    Score add(Score score);

    default int size() {
        return pinCounts().pinCounts().size();
    }
}
