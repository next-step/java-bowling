package bowling.domain;

import bowling.exception.CannotBowlException;

public class PinCount {

  public static final int STRIKE = 10;
  public static final int GUTTER = 0;

  private int pinCount;

  public PinCount(int pinCount) {
    if (pinCount > STRIKE || pinCount < GUTTER) {
      throw new IllegalArgumentException("볼링핀은 0개 ~ 10개까지만 칠 수 있습니다.");
    }

    this.pinCount = pinCount;
  }

  public int getPinCount() {
    return pinCount;
  }

  public boolean isSpareOf(PinCount first) {
    return pinCount != GUTTER && first.pinCount + pinCount == STRIKE;
  }

  public void isValidSecondTrialOf(PinCount first) throws CannotBowlException {
    if (first.pinCount + pinCount > STRIKE) {
      throw new CannotBowlException("볼링핀을 10개 이상 칠 수 없습니다.");
    }
  }

  public boolean isStrike() {
    return pinCount == STRIKE;
  }

  public boolean isGutter() {
    return pinCount == GUTTER;
  }
}
