package bowling.domain;

public interface Frame {

    void pitch(int count);

    boolean isFinish();

    Frame next();

    String getFallenPins();

    int getScore();

    void calculateScore(int index, int count);

    boolean hasScore();
}
