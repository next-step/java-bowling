package bowling.pin;

import java.util.Objects;

public class Pin {

  private static final int MAX_PINS = 10;
  private static final int MIN_PINS = 0;
  private static final String STRIKE = "X";
  private static final String SEPARATOR = "|";
  private static final String SPARE = "/";

  private final int fallenPin;

  private Pin(final int fallenPin) {
    this.fallenPin = fallenPin;
  }

  public static Pin from(final int fallenPin) {
    validationPins(fallenPin);
    return new Pin(fallenPin);
  }

  private static void validationPins(final int fallenPin) {
    if (fallenPin > MAX_PINS) {
      throw new IllegalArgumentException(String.format("입력받은 핀의 수가 %d보다 클 수 없습니다.", MAX_PINS));
    }

    if (fallenPin < MIN_PINS) {
      throw new IllegalArgumentException(String.format("입력받은 핀의 수가 %d보다 작을 수 없습니다.", MIN_PINS));
    }
  }

  public int totalDownPin(final Pin firstPin) {
    if (this.fallenPin + firstPin.fallenPin > MAX_PINS) {
      throw new IllegalArgumentException(String.format("쓰러트린 핀의 총합이 %d보다 클 수 없습니다.", MAX_PINS));
    }
    return this.fallenPin + firstPin.fallenPin;
  }

  public boolean isStrike() {
    return this.fallenPin == MAX_PINS;
  }

  public boolean isSpare(final Pin firstPin) {
    return totalDownPin(firstPin) == MAX_PINS;
  }

  public boolean isMiss(final Pin firstPin) {
    return totalDownPin(firstPin) < MAX_PINS;
  }

  public String score() {
    if (isStrike()) {
      return STRIKE;
    }
    return this.fallenPin + SEPARATOR;
  }

  public String score(Pin first) {
    if (isSpare(first)) {
      return first.fallenPin + SEPARATOR + SPARE;
    }
    return first.fallenPin + SEPARATOR + this.fallenPin;
  }


  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final Pin pins = (Pin) o;
    return fallenPin == pins.fallenPin;
  }

  @Override
  public int hashCode() {
    return Objects.hash(fallenPin);
  }
}
