package bowling.domain.state;

import bowling.domain.PinCount;
import bowling.domain.Score;

public interface State {

  State play(PinCount pinCount);

  boolean isEnd();

  boolean isBonus();

  String getString();

  Score getScore();
}
