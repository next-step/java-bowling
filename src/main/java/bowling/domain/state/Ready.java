package bowling.domain.state;

public class Ready implements  State {

  @Override
  public State play(int pinCount) {
    if (pinCount == 10) {
      return new Strike();
    }

    if (pinCount == 0) {
      return new FirstGutter();
    }

    return new Hit(pinCount);
  }

  @Override
  public boolean isEnd() {
    return false;
  }
}
