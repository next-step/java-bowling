package bowling.domain.state;

public class Strike implements  State {
  private static final String END = "더이상 진행할 수 없습니다.";

  @Override
  public State play(int pinCount) {
    throw new IllegalArgumentException(END);
  }

  @Override
  public boolean isEnd() {
    return true;
  }

  @Override
  public boolean isBonus() {
    return true;
  }
}
