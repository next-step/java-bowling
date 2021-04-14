package bowling.domain.state;

public class FirstGutter implements State {

  @Override
  public State play(int pinCount) {
    if (pinCount == 0) {
      return new SecondGutter();
    }
    if (pinCount == 10) {
      return new Spare();
    }
    return new Miss();
  }

  @Override
  public boolean isEnd() {
    return false;
  }
}
