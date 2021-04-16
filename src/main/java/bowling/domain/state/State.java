package bowling.domain.state;

import bowling.domain.PinCount;

public interface State {

  State play(PinCount pinCount);

  boolean isEnd();

  boolean isBonus();

  String getString();
}
