package bowling.domain.frame;

public interface Frame {

    void bowl(int count);

    boolean isEnd();

    Frame next();

    String getFallenPins();

    int getScore();

    void calculateScore(int index, int count);

    boolean hasScore();

}