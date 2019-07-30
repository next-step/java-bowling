package bowling;

import java.util.Objects;

public class Pins {

  private static final String GUTTER_SYMBOL = "-";
  private static final String DEFAULT_SYMBOL = "";
  private static final int MAX_FALL_DOWN_COUNT = 10;
  private static final int MIN_FALL_DOWN_COUNT = 0;

  private int countOfPins;

  public Pins(int countOfPins) {
    if (countOfPins > MAX_FALL_DOWN_COUNT || countOfPins < MIN_FALL_DOWN_COUNT) {
      throw new IllegalArgumentException("한번에 넘어갈 수 있는 핀은 0개 이상 10개 이하입니다.");
    }

    this.countOfPins = countOfPins;
  }

  public boolean isStrike() {
    return countOfPins == MAX_FALL_DOWN_COUNT;
  }

  public boolean isSpare(Pins secondPins) {
    return this.countOfPins + secondPins.count() == MAX_FALL_DOWN_COUNT;
  }

  public int count() {
    return countOfPins;
  }

  public String desc() {
    if (isStrike()) {
      return "X";
    }
    return count() == 0 ? GUTTER_SYMBOL : count() + DEFAULT_SYMBOL;
  }

  public boolean isInvalidPins(Pins pins) {
    return this.countOfPins + pins.count() > MAX_FALL_DOWN_COUNT;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Pins pins = (Pins) o;
    return countOfPins == pins.countOfPins;
  }

  @Override
  public int hashCode() {
    return Objects.hash(countOfPins);
  }
}
