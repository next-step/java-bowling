package bowling.state;

import bowling.pin.Pin;

public class NextPitch extends Running {

  private static final int MAX_PINS = 10;

  private final Pin firstPin;

  private Pin totalPin;

  public NextPitch(final Pin firstPin) {
    this.firstPin = firstPin;
  }

  @Override
  public State nextPitch(final int fallenPin) {
    Pin currentPin = Pin.from(fallenPin);
    totalPin = firstPin.totalDownPin(currentPin);

    if (isSpare(currentPin)) {
      return new Spare(firstPin, currentPin);
    }
    return new Miss(firstPin, currentPin);
  }

  private boolean isSpare(final Pin currentPin) {
    return currentPin.totalDownPin(firstPin).equals(Pin.from(MAX_PINS));
  }

  @Override
  public String score() {
    return String.valueOf(firstPin.totalDownPin());
  }

  @Override
  public Pin totalPin() {
    return totalPin;
  }
}