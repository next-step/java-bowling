package bowling.domain;

public interface Frame {
    Frame bowl(int pins);
    boolean isOver();
    Score getScore();
    Score addBonusScore(Score prevScore);
    Frame getNext();
    String display();
}
