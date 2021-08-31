package bowling.state;

import bowling.pin.Pin;

public class FirstPitch extends Running{

  private static final int PIN_MIN = 0;
  private static final int PIN_MAX = 10;

  private int totalPin;

  @Override
  public State nextPitch(final int fallenPin) {
    Pin currentPin = Pin.from(fallenPin);


    if (currentPin.isStrike()) {
      totalPin = PIN_MAX;
      return new Strike(currentPin);
    }
    totalPin = Pin.from(PIN_MIN).totalDownPin(currentPin);

    return new NextPitch(currentPin);
  }

  @Override
  public String score() {
    return "";
  }

  @Override
  public int totalPin() {
    return totalPin;
  }
}