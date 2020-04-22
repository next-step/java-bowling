package bowling.domain;

import bowling.exception.CannotBowlException;

public class PinCount {

  public static final int MAX_PIN = 10;
  public static final int MIN_PIN = 0;

  private int pinCount;

  public PinCount(int pinCount) {
    if (pinCount > MAX_PIN || pinCount < MIN_PIN) {
      throw new IllegalArgumentException("볼링핀은 0개 ~ 10개까지만 칠 수 있습니다.");
    }

    this.pinCount = pinCount;
  }

  public int getPinCount() {
    return pinCount;
  }

  public boolean isSpareOf(PinCount first) {
    return pinCount != MIN_PIN && first.pinCount + pinCount == MAX_PIN;
  }

  public void isValidSecondTrialOf(PinCount first) throws CannotBowlException {
    if (first.pinCount + pinCount > MAX_PIN) {
      throw new CannotBowlException("볼링핀을 10개 이상 칠 수 없습니다.");
    }
  }

  public boolean isMax() {
    return pinCount == MAX_PIN;
  }

  public boolean isMin() {
    return pinCount == MIN_PIN;
  }
}
