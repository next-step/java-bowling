package bowling.domain.frame;

import bowling.domain.score.Score;

public interface Frame extends Iterable<Frame> {

    Frame bowl(int numberOfPins);
    Frame getNextFrame();
    Score getScore();
    int getFrameNumber();
    int getTotalNumberOfPin();
    boolean isStrike();
    boolean isCompleted();
    boolean isEnd();

}
