package bowling.domain.frame;

import bowling.domain.state.State;

public interface Frame {

  Frame next();

  void play(int pinCount);

  int getPlayCount();

  State getState();

  boolean isEnd();

}
