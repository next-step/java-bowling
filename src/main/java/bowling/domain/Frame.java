package bowling.domain;

public interface Frame {
    boolean isEnd();

    void addRoll(Pin pin);

    Rolls getScores();

    FrameStatus getStatus();

    void updateStatus();
}
