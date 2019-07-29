package bowling;

import java.util.Objects;

public class FallDownPin {

  private static final int MAX_FALL_DOWN_COUNT = 10;
  private static final int MIN_FALL_DOWN_COUNT = 0;
  private static final int GUTTER_COUNT = 0;
  private static final String GUTTER_SYMBOL = "-";

  private int fallDownCount;

  public FallDownPin(int countOfPin) {
    if (countOfPin > MAX_FALL_DOWN_COUNT) {
      throw new IllegalArgumentException("핀은 한번에 " + MAX_FALL_DOWN_COUNT + "개 초과로 넘어가지 못합니다.");
    }
    if (countOfPin < MIN_FALL_DOWN_COUNT) {
      throw new IllegalArgumentException("핀이 넘어간 수는 음수가 될 수 없습니다.");
    }
    this.fallDownCount = countOfPin;
  }

  public static FallDownPin of(int countOfPin) {
    return new FallDownPin(countOfPin);
  }

  public boolean isValidCount(int fallDownCount) {
    return this.fallDownCount + fallDownCount <= MAX_FALL_DOWN_COUNT;
  }

  public boolean isStrike() {
    return fallDownCount == MAX_FALL_DOWN_COUNT;
  }

  public boolean isSpare(FallDownPin secondFallDown) {
    return this.fallDownCount + secondFallDown.getFallDownCount() == MAX_FALL_DOWN_COUNT;
  }

  public int getFallDownCount() {
    return fallDownCount;
  }

  private boolean isGutter() {
    return fallDownCount == GUTTER_COUNT;
  }

  @Override
  public String toString() {
    if (isGutter()) {
      return GUTTER_SYMBOL;
    }
    return String.valueOf(fallDownCount);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FallDownPin that = (FallDownPin) o;
    return fallDownCount == that.fallDownCount;
  }

  @Override
  public int hashCode() {
    return Objects.hash(fallDownCount);
  }

}
