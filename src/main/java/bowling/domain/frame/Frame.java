package bowling.domain.frame;

import bowling.domain.PinCount;
import bowling.domain.state.State;

public interface Frame {

  Frame next();

  void play(PinCount pinCount);

  int getPlayCount();

  State getState();

  boolean isEnd();

}
