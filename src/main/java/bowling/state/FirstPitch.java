package bowling.state;

import bowling.pin.Pin;

public class FirstPitch extends Running{

  @Override
  public State nextPitch(final int fallenPin) {
    Pin currentPin = Pin.from(fallenPin);
    if (currentPin.isStrike()) {
      return new Strike(currentPin);
    }
    return new NextPitch(currentPin);
  }

  @Override
  public String score() {
    return "";
  }
}
