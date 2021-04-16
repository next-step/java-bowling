package bowling.domain.state;

import bowling.domain.PinCount;

public class FirstGutter implements State {

  @Override
  public State play(PinCount pinCount) {
    if (pinCount.isGutter()) {
      return new SecondGutter();
    }

    if (pinCount.isStrike()) {
      return new Spare();
    }

    return new Miss(pinCount);
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
}
