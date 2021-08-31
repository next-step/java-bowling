package bowling.state;

import bowling.pin.Pin;

public class NextPitch extends Running {

  private final Pin firstPin;

  private int totalPin;

  public NextPitch(final Pin firstPin) {
    this.firstPin = firstPin;
  }

  @Override
  public State nextPitch(final int fallenPin) {
    Pin currentPin = Pin.from(fallenPin);
    totalPin = firstPin.totalDownPin(currentPin);

    if (currentPin.isSpare(firstPin)) {
      return new Spare(firstPin, currentPin);
    }

    return new Miss(firstPin, currentPin);
  }

  @Override
  public String score() {
    return firstPin.score();
  }

  @Override
  public int totalPin() {
    return totalPin;
  }
}