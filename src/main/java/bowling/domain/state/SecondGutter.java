package bowling.domain.state;

public class SecondGutter implements State {

  private static final String END_PLAY = "더이상 진행할 수 없습니다.";
  @Override
  public State play(int pinCount) {
    throw new IllegalArgumentException(END_PLAY);
  }

  @Override
  public boolean isEnd() {
    return true;
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
