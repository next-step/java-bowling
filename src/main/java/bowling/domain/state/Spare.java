package bowling.domain.state;

public class Spare implements State {

  @Override
  public State play(int pinCount) {
    return null;
  }

  @Override
  public boolean isEnd() {
    return true;
  }
}
