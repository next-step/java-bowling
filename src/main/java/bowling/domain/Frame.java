package bowling.domain;

public interface Frame {
    boolean end();

    void addScore(Pin pin);

    Rolls getScores();

    FrameResult getResult();
}
