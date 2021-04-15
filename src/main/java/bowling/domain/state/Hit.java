package bowling.domain.state;

public class Hit implements  State{

  private final int pinCount;

  public Hit(int pinCount) {
    this.pinCount = pinCount;
  }

  @Override
  public State play(int newPinCount) {
    if(newPinCount == 0) {
      return new SecondGutter();
    }

    if(pinCount + newPinCount == 10) {
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
    return String.valueOf(pinCount);
  }
}
