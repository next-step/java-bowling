package bowling.domain.frame;

import bowling.domain.PinCount;
import bowling.domain.state.State;

public interface Frame {

  int getPlayCount();

  State getState();

  boolean isEnd();

  void play(PinCount pinCount);

  Frame nextFrame();
}
