package bowling.state;

import bowling.pin.Pin;

public class FirstPitch extends Running{

  private Pin currentPin;

  public FirstPitch() {
  }

  @Override
  public State nextPitch(final int fallenPin) {
    currentPin = Pin.from(fallenPin);

    if (currentPin.isStrike()) {
      return new Strike(currentPin);
    }

    return new NextPitch(currentPin);
  }

  @Override
  public String score() {
    return "";
  }

  @Override
  public int totalPin() {
    return currentPin.totalDownPin();
  }
}