package bowling.pin;

import bowling.score.Score;
import java.util.Objects;

public class Pin {

  private static final int MAX_PINS = 10;
  private static final int MIN_PINS = 0;

  private static final String MSG_ERROR_PIN_MAX = "입력받은 핀의 수가 %d보다 클 수 없습니다.";
  private static final String MSG_ERROR_PIN_MIN = "입력받은 핀의 수가 %d보다 작을 수 없습니다.";
  private static final String MSG_ERROR_DOWN_PIN_MAX = "쓰러트린 핀의 총합이 %d보다 클 수 없습니다.";

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
      throw new IllegalArgumentException(String.format(MSG_ERROR_PIN_MAX, MAX_PINS));
    }

    if (fallenPin < MIN_PINS) {
      throw new IllegalArgumentException(String.format(MSG_ERROR_PIN_MIN, MIN_PINS));
    }
  }

  public Pin totalDownPin(final Pin firstPin) {
    if (this.fallenPin + firstPin.fallenPin > MAX_PINS) {
      throw new IllegalArgumentException(String.format(MSG_ERROR_DOWN_PIN_MAX, MAX_PINS));
    }
    return from(this.fallenPin + firstPin.fallenPin);
  }

  public int pinCount(){
    return this.fallenPin;
  }

  public Score sumScore(final Score firstScore) {
    return firstScore.sum(this.fallenPin);
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

  @Override
  public String toString() {
    return String.valueOf(fallenPin);
  }

}
