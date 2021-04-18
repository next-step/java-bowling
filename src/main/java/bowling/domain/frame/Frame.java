package bowling.domain.frame;

public interface Frame {

    void play(int count);

    boolean isEnd();

    Frame next();

    String getFallenPins();

    int getScore();

    void calculateScore(int index, int count);

    boolean hasScore();
}
