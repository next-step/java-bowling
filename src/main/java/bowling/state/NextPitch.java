package bowling.state;

import bowling.pin.Pin;

public class NextPitch extends Running {

  private final Pin firstPin;

  public NextPitch(final Pin firstPin) {
    this.firstPin = firstPin;
  }

  @Override
  public State nextPitch(final int fallenPin) {
    Pin currentPin = Pin.from(fallenPin);

    if (currentPin.isSpare(firstPin)) {
      return new Spare(firstPin, currentPin);
    }

    return new Miss(firstPin, currentPin);
  }

  @Override
  public String score() {
    return firstPin.score();
  }
}