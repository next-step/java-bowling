package bowling.domain.state;

import bowling.domain.PinCount;
import bowling.domain.Score;

public class FirstGutter implements State {

  @Override
  public State play(PinCount newPinCount) {
    if (newPinCount.isGutter()) {
      return new SecondGutter();
    }

    if (newPinCount.isStrike()) {
      return new Spare(new PinCount(0), newPinCount);
    }

    return new Miss(new PinCount(0),newPinCount);
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
    return "-";
  }

  @Override
  public Score getScore() {
    return new Score(0, 0);
  }
}
