package bowling.domain.state;

public class Miss implements State {

  private int pinCount;
  private static final String END = "더이상 진행할 수 없습니다.";

  public Miss(int pinCount) {
    this.pinCount = pinCount;
  }


  @Override
  public State play(int pinCount) {
    throw new IllegalArgumentException(END);
  }

  @Override
  public boolean isEnd() {
    return true;
  }
}
