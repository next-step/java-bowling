package bowling.domain.state;

public class Gutter implements State {

  @Override
  public State play(int pinCount) {
    return null;
  }

  @Override
  public boolean isEnd() {
    return false;
  }
}
