package bowling.domain.frame;

import bowling.domain.score.Score;

public interface Frame extends Iterable<Frame> {

    Frame bowl(int numberOfPins);
    Frame getNextFrame();
    int getFrameNumber();
    Score getScore();
    boolean isCompleted();
    int getTotalNumberOfPin();
    boolean isEnd();

}
