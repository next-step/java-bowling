package bowling.domain.state;

public class Miss implements State {

  private final int pinCount;
  private static final String END_PLAY = "더이상 진행할 수 없습니다.";

  public Miss(int pinCount) {
    this.pinCount = pinCount;
  }


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
    return String.valueOf(pinCount);
  }
}
