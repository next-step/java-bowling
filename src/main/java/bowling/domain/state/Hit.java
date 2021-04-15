package bowling.domain.state;

public class Hit implements State {

  private final int pinCount;
  private static final String INVALID_TOTAL_PINCOUNT = "총 친 핀의 개수는 0개 이상 10개 이하여야 합니다.";
  private static final String INVALID_PINCOUNT = "핀의 개수는 0개 이상 10개 이하여야 합니다.";

  public Hit(int pinCount) {
    validatePinCount(pinCount);
    this.pinCount = pinCount;
  }

  @Override
  public State play(int newPinCount) {
    validateNewPinCount(newPinCount);
    if (newPinCount == 0) {
      return new SecondGutter();
    }

    if (pinCount + newPinCount == 10) {
      return new Spare();
    }

    return new Miss(newPinCount);
  }


  private void validateNewPinCount(int newPinCount) {
    if (pinCount + newPinCount > 10 || pinCount + newPinCount < 0) {
      throw new IllegalArgumentException(INVALID_TOTAL_PINCOUNT);
    }
  }

  private void validatePinCount(int pinCount) {
    if (pinCount > 10 || pinCount < 0) {
      throw new IllegalArgumentException(INVALID_PINCOUNT);
    }
  }

  @Override
  public boolean isEnd() {
    return false;
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
