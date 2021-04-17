package bowling.domain.state;

import bowling.domain.PinCount;
import bowling.domain.Score;

public class Ready implements State {

  @Override
  public State play(PinCount pinCount) {
    if (pinCount.isStrike()) {
      return new Strike();
    }

    if (pinCount.isGutter()) {
      return new FirstGutter();
    }

    return new Hit(pinCount);
  }

  @Override
  public boolean isEnd() {
    return false;
  }

  @Override
  public boolean isBonus() {
    return false;
  }

  @Override
  public String getString() {
    return "";
  }

  @Override
  public Score getScore() {
    return new Score(0, 0);
  }
}
