package bowling.state;

import bowling.pin.Pin;

public class FirstPitch extends Running {

  private static final int MAX_PINS = 10;

  private Pin currentPin;

  public FirstPitch() {
  }

  @Override
  public State nextPitch(final int fallenPin) {
    currentPin = Pin.from(fallenPin);

    if (isStrike()) {
      return new Strike(currentPin);
    }
    return new NextPitch(currentPin);
  }

  private boolean isStrike() {
    return currentPin.equals(Pin.from(MAX_PINS));
  }

  @Override
  public String scoreMessage() {
    return "";
  }

  @Override
  public Pin totalPin() {
    return currentPin;
  }
}