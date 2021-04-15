package bowling.domain.state;

public class Miss implements State {

  private final int pinCount;
  private static final String END_PLAY = "더이상 진행할 수 없습니다.";
  private static final String INVALID_PINCOUNT = "핀의 개수는 0개 이상 10개 이하여야 합니다.";

  public Miss(int pinCount) {
    validatePinCount(pinCount);
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

  private void validatePinCount(int pinCount) {
    if (pinCount > 10 || pinCount < 0) {
      throw new IllegalArgumentException(INVALID_PINCOUNT);
    }
  }
}
