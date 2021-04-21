package bowling.domain.frame;

import bowling.domain.Pin;

public abstract class Frame {

  abstract boolean isEnd();

  abstract Frame next();

  abstract int getScore();

  void calculateScore(int index, Pin count) {

  }

  abstract boolean hasScore();

  abstract void play(Pin pinCount);
}
