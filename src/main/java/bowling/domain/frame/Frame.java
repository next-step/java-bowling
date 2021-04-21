package bowling.domain.frame;

import bowling.domain.Pin;

public interface Frame {

    boolean isEnd();

    Frame next();

    String getFallenPins();

    int getScore();

    void calculateScore(int index, Pin count);

    boolean hasScore();

    void play(Pin pinCount);
}
