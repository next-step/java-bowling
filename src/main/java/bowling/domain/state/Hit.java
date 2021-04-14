package bowling.domain.state;

public class Hit implements  State{

  private int pinCount;

  public Hit(int pinCount) {
    this.pinCount = pinCount;
  }

  @Override
  public State play(int newPinCount) {
    if(newPinCount == 0) {
      return new Gutter();
    }

    if(pinCount + newPinCount == 10) {
      return new Spare();
    }

    return new Miss();
  }

  @Override
  public boolean isEnd() {
    return false;
  }
}
