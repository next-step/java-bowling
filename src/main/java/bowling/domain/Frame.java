package bowling.domain;

public interface Frame {
    boolean isOver();
    void add(int value);
    Frame next();
    String display();
    int getScore();
    Score addPreviousScore(Score score);
    Frame getNext();
}
