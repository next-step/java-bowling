package bowling.domain.state;

public class Strike implements  State {

  @Override
  public State play(int pinCount) {
    return null;
  }

  @Override
  public boolean isEnd() {
    return true;
  }
}
