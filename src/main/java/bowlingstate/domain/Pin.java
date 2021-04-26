package bowlingstate.domain;

public class Pin {

  private static final int MIN_HIT_PIN = 0;
  private static final int MAX_HIT_PIN = 10;
  private final int pin;

  public Pin(int pin) {
    validateHitPin(pin);
    this.pin = pin;
  }

  private void validateHitPin(int pin) {
    if (pin < MIN_HIT_PIN || pin > MAX_HIT_PIN) {
      throw new IllegalArgumentException("볼링 핀은 0에서 10 사이값만 입력할 수 있습니다.");
    }
  }

  public int pin() {
    return pin;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Pin pin1 = (Pin) o;

    return pin == pin1.pin;
  }

  @Override
  public int hashCode() {
    return pin;
  }
}
