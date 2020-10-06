package bowling.domain;

public interface Frame {

    void pitch(int count);

    boolean isEnd();

    Frame next();

    String getFallenPins();

    int getScore();

    void calculateScore(int index, int count);

    boolean hasScore();
}
