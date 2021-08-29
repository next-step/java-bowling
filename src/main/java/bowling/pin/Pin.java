package bowling.pin;

import java.util.Objects;

public class Pin {

  public static final int MAX_PINS = 10;
  public static final int MIN_PINS = 0;
  private final int fallenPin;

  private Pin(final int fallenPin) {
    this.fallenPin = fallenPin;
  }

  public static Pin from(final int fallenPin) {
    validationPins(fallenPin);
    return new Pin(fallenPin);
  }

  private static void validationPins(final int fallenPin) {
    if(fallenPin > MAX_PINS){
      throw new IllegalArgumentException(String.format("입력받은 핀의 수가 %d보다 클 수 없습니다.", MAX_PINS));
    }

    if(fallenPin < MIN_PINS){
      throw new IllegalArgumentException(String.format("입력받은 핀의 수가 %d보다 작을 수 없습니다.", MIN_PINS));
    }
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
