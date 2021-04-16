package bowling.domain.state;

import bowling.domain.PinCount;

public class Hit implements State {

  private final PinCount pinCount;

  public Hit(PinCount pinCount) {
    this.pinCount = pinCount;
  }

  public Hit(int pinCount) {
    this(new PinCount(pinCount));
  }

  @Override
  public State play(PinCount newPinCount) {

    pinCount.validateNewPinCount(newPinCount);

    if (newPinCount.isGutter()) {
      return new SecondGutter();
    }

    if (pinCount.totalSumIsTen(newPinCount)) {
      return new Spare();
    }

    return new Miss(newPinCount);
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
    return String.valueOf(pinCount.getPinCount());
  }
}
